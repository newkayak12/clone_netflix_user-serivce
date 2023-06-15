package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.components.validations.ProfileValid;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileImageRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileModifyRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileSaveRequest;
import com.netflix_clone.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@RestController
@RequestMapping(value = "/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProfileDto>> profiles(@ModelAttribute ProfileRequest profileRequest){
        return new ResponseEntity(service.profiles(profileRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{profileNo:[\\d]+}")
    public ResponseEntity<ProfileDto> profile(@PathVariable Long profileNo, @ModelAttribute MobileDeviceInfoDto mobileDeviceInfoDto) {
        return new ResponseEntity<ProfileDto>(service.profile(profileNo, mobileDeviceInfoDto), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProfileDto> saveProfile(@ModelAttribute @Valid @Validated(value = {ProfileValid.Save.class})
                                                  ProfileSaveRequest profileSaveRequest) throws CommonException {
        return new ResponseEntity<>(service.saveProfile(profileSaveRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{profileNo:[\\d]+}/profileName")
    public ResponseEntity<Boolean> changeProfileName(@ModelAttribute @Valid @Validated(value = {ProfileValid.ChangeProfileName.class})
                                                     ProfileModifyRequest profileNameRequest) throws CommonException {
        return new ResponseEntity<Boolean>(service.changeProfileName(profileNameRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{profileNo:[\\d]+}/isPush")
    public ResponseEntity<Boolean> changePushState(@ModelAttribute @Valid @Validated(value = {ProfileValid.PushState.class})
                                                   ProfileModifyRequest profileNameRequest) throws CommonException {
        return new ResponseEntity<Boolean>(service.changePushState(profileNameRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/{profileNo:[\\d]+}/profileImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDto> changeProfileImage (@ModelAttribute ProfileImageRequest profileImageRequest) throws CommonException {
        return new ResponseEntity<FileDto>(service.changeProfileImage(profileImageRequest), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{profileNo:[\\d]+}")
    public ResponseEntity<Boolean> removeProfile(@PathVariable Long profileNo){
        return ResponseEntity.ok(service.removeProfile(profileNo));
    }
}
