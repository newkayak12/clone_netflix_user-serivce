package com.netflix_clone.userservice.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(value = {
        "userNo",
        "regDate",
        "isAdult",
        "adultCheckDate",
        "mobileNo",
        "email",
        "isSubscribed",
        "profiles",
})
public class SignInRequest extends AccountDto {
    private MobileDeviceInfoDto deviceInfo;
}
