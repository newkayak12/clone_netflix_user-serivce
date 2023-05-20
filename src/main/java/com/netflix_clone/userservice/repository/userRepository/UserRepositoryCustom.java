package com.netflix_clone.userservice.repository.userRepository;

import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;

public interface UserRepositoryCustom {
    AccountDto signIn(SignInRequest accountDto);
    Boolean changePassword(ChangePasswordRequest changePasswordRequest);

}
