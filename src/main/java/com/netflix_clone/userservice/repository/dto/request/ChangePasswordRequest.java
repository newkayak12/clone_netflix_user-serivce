package com.netflix_clone.userservice.repository.dto.request;

/**
 * Created on 2023-05-19
 * Project user-service
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix_clone.userservice.components.validations.AccountValid;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = {
        "regDate",
        "isAdult",
        "adultCheckDate",
        "mobileNo",
        "email",
        "isSubscribed",
        "profiles",
})
public class ChangePasswordRequest extends AccountDto {
    @NotEmpty(message = "변경할 비밀번호를 입력하세요.", groups = {AccountValid.changePwd.class})
    private String newUserPwd;
}
