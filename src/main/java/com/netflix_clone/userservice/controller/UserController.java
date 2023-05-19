package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.configure.feign.BoardFeign;
import com.netflix_clone.userservice.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.repository.dto.request.SignUpRequest;
import com.netflix_clone.userservice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

/**
 * Created on 2023-05-12
 * Project user-service
 */
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final BoardFeign boardFeign;
    private final UserService service;

    @GetMapping(value = "/sign/in")
    public ResponseEntity<AccountDto> signIn(@ModelAttribute SignInRequest signInRequest) throws CommonException {
        return new ResponseEntity<>(service.signIn(signInRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/check/id/{userId}")
    public ResponseEntity<Boolean> checkDuplicateId(@PathVariable String userId){
        return new ResponseEntity<>(service.checkDuplicateId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/sign/up")
    public ResponseEntity<AccountDto> signUp(@RequestBody SignUpRequest signUpRequest) throws CommonException {
        return new ResponseEntity<>(service.signUp(signUpRequest), HttpStatus.OK);
    }

    @PatchMapping(value = "/change/password")
    public ResponseEntity<Boolean> changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest) throws CommonException {
        return new ResponseEntity<>(service.changePassword(changePasswordRequest), HttpStatus.OK);
    }

    // TODO - find id, find pwd
}
