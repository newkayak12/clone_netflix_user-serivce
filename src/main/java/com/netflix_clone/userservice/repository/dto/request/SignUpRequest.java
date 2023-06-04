package com.netflix_clone.userservice.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = {
        "userNo",
        "regDate",
        "isAdult",
        "adultCheckDate",
        "isSubscribed",
        "profiles",
})
public class SignUpRequest extends AccountDto {

    public SignUpRequest(String userId, String userPwd, String mobileNo, String email) {
        super(null, userId, userPwd, null, false, null, mobileNo, email, false, null);
//        this.deviceInfo = deviceInfo;
    }
//    private MobileDeviceInfoDto deviceInfo;
}
