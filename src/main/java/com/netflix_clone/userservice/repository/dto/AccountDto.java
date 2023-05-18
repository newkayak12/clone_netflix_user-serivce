package com.netflix_clone.userservice.repository.dto;

import com.netflix_clone.userservice.repository.domains.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto implements Serializable {
    private Long userNo;
    private String userId;
    private String userPwd;
    private LocalDateTime regDate;
    private Boolean isAdult;
    private LocalDateTime adultCheckDate;
    private String mobileNo;
    private String email;
    private Boolean isSubscribed;
    private List<ProfileDto> profiles = new ArrayList<>();
}
