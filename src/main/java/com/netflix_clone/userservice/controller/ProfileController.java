package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileImageRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileModifyRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;
import com.netflix_clone.userservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{profileNo}")
    public ResponseEntity<ProfileDto> profile(@PathVariable Long profileNo, @ModelAttribute MobileDeviceInfoDto mobileDeviceInfoDto) {
        return new ResponseEntity<ProfileDto>(service.profile(profileNo, mobileDeviceInfoDto), HttpStatus.OK);
    }

    @PatchMapping(value = "/{profileNo}/profileName")
    public ResponseEntity<Boolean> changeProfileName(@ModelAttribute ProfileModifyRequest profileNameRequest) throws CommonException {
        return new ResponseEntity<Boolean>(service.changeProfileName(profileNameRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/{profileNo}/isPush")
    public ResponseEntity<Boolean> changePushState(@ModelAttribute ProfileModifyRequest profileNameRequest) throws CommonException {
        return new ResponseEntity<Boolean>(service.changePushState(profileNameRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/{profileNo}/profileImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDto> changeProfileImage (@ModelAttribute ProfileImageRequest profileImageRequest) throws CommonException {
        return new ResponseEntity<FileDto>(service.changeProfileImage(profileImageRequest), HttpStatus.OK);
    }

}
