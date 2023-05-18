package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.exceptions.BecauseOf;
import com.netflix_clone.userservice.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.netflix_clone.userservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Boolean isPasswordMatched(String rawPassword, String encryptedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
    public AccountDto signIn(SignInRequest signInRequest) throws CommonException {
        AccountDto dto = Optional.ofNullable(repository.signIn(signInRequest)).orElseThrow(() -> new CommonException(BecauseOf.ACCOUNT_NOT_EXIST));
        if(!this.isPasswordMatched(signInRequest.getUserPwd(), dto.getUserPwd())) throw new CommonException(BecauseOf.PASSWORD_NOT_MATCHED);


        // TODO: MOBILE DEVICE INFO


        return dto;
    }
}
