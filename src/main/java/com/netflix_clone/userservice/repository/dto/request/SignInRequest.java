package com.netflix_clone.userservice.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
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
    public SignInRequest(String userId, String userPwd, MobileDeviceInfoDto deviceInfo) {
        super(null, userId, userPwd, null, null, null, null, null, null, null);
//        this.deviceInfo = deviceInfo;
    }

//    private MobileDeviceInfoDto deviceInfo;
}
