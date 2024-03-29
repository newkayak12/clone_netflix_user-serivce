package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.components.configure.rabbit.RabbitPublisher;
import com.netflix_clone.userservice.components.delegate.ImageDelegate;
import com.netflix_clone.userservice.components.enums.FileType;
import com.netflix_clone.userservice.components.enums.Rabbit;
import com.netflix_clone.userservice.components.exceptions.BecauseOf;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.deviceRepository.DeviceRepository;
import com.netflix_clone.userservice.repository.domains.MobileDeviceInfo;
import com.netflix_clone.userservice.repository.domains.Profile;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.FileRequest;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileImageRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileModifyRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileSaveRequest;
import com.netflix_clone.userservice.repository.profileRepository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProfileService {
    private final ModelMapper mapper;
    private final ProfileRepository repository;
    private final DeviceRepository deviceRepository;
    private final ImageFeign imageFeign;
    private final ImageDelegate imageDelegate;
    private final RabbitPublisher rabbitPublisher;

    private Boolean isDeviceInfoChanged(Long profileNo, MobileDeviceInfoDto mobileDeviceInfoDto){
        MobileDeviceInfoDto dto = deviceRepository.findByProfileNo(profileNo);
        return !Objects.requireNonNullElseGet(dto, MobileDeviceInfoDto::new).equals(mobileDeviceInfoDto);
    }
    private MobileDeviceInfoDto changeDeviceInfo(Long profileNo, MobileDeviceInfoDto mobileDeviceInfoDto){
        mobileDeviceInfoDto.setProfileId(profileNo);
        return mapper.map(
                deviceRepository.save(mapper.map(mobileDeviceInfoDto, MobileDeviceInfo.class)),
                MobileDeviceInfoDto.class
        );
    }


    @Transactional(readOnly = true)
    public List<ProfileDto> profiles(ProfileRequest profileRequest) {
        List<ProfileDto> profiles = repository.profiles(profileRequest);
        if( !profiles.isEmpty() ) {
            List<FileDto> images = Optional.ofNullable(
                    imageFeign.files(profiles.stream().map(ProfileDto::getProfileNo)
                            .collect(Collectors.toList()), FileType.PROFILE)
                    ).map(value -> value.getBody()).orElseGet(() -> new ArrayList<>());
            profiles.forEach( profile -> profile.setImage( imageDelegate.setImage(images, profile.getProfileNo())));

        }


        return profiles;
    }

    public ProfileDto profile(Long profileNo, MobileDeviceInfoDto mobileDeviceInfoDto) {
        log.warn("PROFILENO {}", profileNo);
        ProfileDto profileDto = repository.findByProfileNo(profileNo);
        profileDto.setImage(Optional.ofNullable(imageFeign.file(profileNo, FileType.PROFILE)).map(file -> file.getBody()).orElseGet(() -> null));

        if(Objects.nonNull(mobileDeviceInfoDto) && this.isDeviceInfoChanged(profileNo, mobileDeviceInfoDto)){
            profileDto.setDeviceInfo(this.changeDeviceInfo(profileNo, mobileDeviceInfoDto));
        }

        return profileDto;
    }

    public Boolean changeProfileName(ProfileModifyRequest profileNameRequest) throws CommonException {
        if ( !repository.isChangeProfileName(profileNameRequest) ) throw new CommonException(BecauseOf.UPDATE_FAILURE);
        return true;
    }

    public Boolean changePushState(ProfileModifyRequest profileNameRequest) throws CommonException {
        if( !repository.isChangePushState( profileNameRequest) ) throw new CommonException(BecauseOf.UPDATE_FAILURE);
        return true;
    }

    public FileDto changeProfileImage(ProfileImageRequest profileImageRequest) throws CommonException {

        imageFeign.remove(profileImageRequest.getProfileNo(), FileType.PROFILE);

        FileRequest fileRequest = new FileRequest();
        fileRequest.setTableNo(profileImageRequest.getProfileNo());
        fileRequest.setRawFile(profileImageRequest.getRawFile());
        fileRequest.setFileType(FileType.PROFILE.name());
        if( Objects.nonNull(profileImageRequest.getProfileNo()) ) fileRequest.setFileNo(profileImageRequest.getProfileNo());

        FileDto result = Optional.ofNullable(imageFeign.save(fileRequest).getBody())
                                 .orElseThrow(() -> new CommonException(BecauseOf.UPDATE_FAILURE));

        return result;
    }

    public ProfileDto saveProfile(ProfileSaveRequest profileSaveRequest) throws CommonException {
        if(repository.countProfileByAccount_UserNo(profileSaveRequest.getAccount().getUserNo()) >= 4)
            throw new CommonException(BecauseOf.EXCEED_MAXIMUM_PROFILE_COUNT);

        Profile profile = mapper.map(profileSaveRequest, Profile.class);
        Long profileNo = mapper.map(repository.save(profile), ProfileDto.class).getProfileNo();
        ProfileDto result = mapper.map(repository.findByProfileNo(profileNo), ProfileDto.class);

        if(Objects.nonNull(profileSaveRequest.getDeviceInfo())){
            MobileDeviceInfoDto deviceInfoDto = profileSaveRequest.getDeviceInfo();
            deviceInfoDto.setProfileId(profileNo);

            deviceRepository.save(mapper.map(deviceInfoDto, MobileDeviceInfo.class));
            result.setDeviceInfo(deviceInfoDto);
        }

        if(Objects.nonNull(profileSaveRequest.getRawFile())) {
            FileRequest request = new FileRequest();
            request.setRawFile(profileSaveRequest.getRawFile());
            request.setTableNo(profileNo);
            request.setFileType(FileType.PROFILE.name());

            FileDto fileDto = Optional.ofNullable(imageFeign.save(request).getBody()).orElseGet(() -> null);
            result.setImage(fileDto);
        }

//        rabbitPublisher.send(Rabbit.Topic.USER.getName(), Rabbit.RoutingKey.PROFILE_SAVE.name(), result);

        return result;
    }

    public Boolean removeProfile(Long profileNo) {
        if( repository.removeProfile(profileNo) ) {
            imageFeign.remove(profileNo, FileType.PROFILE);
            return true;
        }
        return false;
    }
}
