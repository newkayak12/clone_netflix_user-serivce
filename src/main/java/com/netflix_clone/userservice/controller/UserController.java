package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.FindAccountRequest;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.repository.dto.request.SignUpRequest;
import com.netflix_clone.userservice.service.UserService;
import com.netflix_clone.userservice.components.validations.AccountValid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created on 2023-05-12
 * Project user-service
 */
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;



    @GetMapping(value = "/sign/in")
    public ResponseEntity<AccountDto> signIn(@ModelAttribute @Valid @Validated(value = {AccountValid.SignIn.class})
                                             SignInRequest signInRequest, HttpServletResponse response) throws CommonException {
        return new ResponseEntity<>(service.signIn(signInRequest, response), HttpStatus.OK);
    }

    @GetMapping(value = "/check/id/{userId}")
    public ResponseEntity<Boolean> checkDuplicateId(@PathVariable String userId){
        return new ResponseEntity<>(service.checkDuplicateId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/sign/up")
    public ResponseEntity<AccountDto> signUp(@RequestBody @Valid @Validated(value = {AccountValid.SignUp.class})
                                             SignUpRequest signUpRequest,
                                             HttpServletResponse response) throws CommonException {
        return new ResponseEntity<>(service.signUp(signUpRequest, response), HttpStatus.OK);
    }

    @PatchMapping(value = "/change/password")
    public ResponseEntity<Boolean> changePassword(@ModelAttribute ChangePasswordRequest changePasswordRequest) throws CommonException {
        return new ResponseEntity<>(service.changePassword(changePasswordRequest), HttpStatus.OK);
    }

    @GetMapping(value = "/find/id/")
    public ResponseEntity<String> findId(@ModelAttribute @Valid @Validated(value = {AccountValid.FindId.class})
                                         FindAccountRequest request) throws CommonException {
        return new ResponseEntity<>(service.findId(request), HttpStatus.OK);
    }
    // TODO - find pwd
}
