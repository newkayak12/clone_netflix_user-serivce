package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.configure.feign.BoardFeign;
import com.netflix_clone.userservice.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public AccountDto signIn(@ModelAttribute SignInRequest signInRequest) throws CommonException {
        return service.signIn(signInRequest);
    }

}
