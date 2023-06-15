package com.netflix_clone.userservice.repository.dto.reference;

import com.netflix_clone.userservice.components.validations.ProfileValid;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto implements Serializable {
    private Long profileNo;
    private AccountDto account;

    @NotEmpty(message = "프로필 이름을 입력하세요.", groups = {ProfileValid.Save.class, ProfileValid.ChangeProfileName.class})
    private String profileName;
    private LocalDateTime regDate;
    private Boolean isPush;
    @DateTimeFormat(pattern = "yyyy-MM-dd`T`hh:mm:ss")
    private LocalDateTime lastSignInDate;

    @Transient
    private FileDto image;
    @Transient
    private MobileDeviceInfoDto deviceInfo;


    @QueryProjection
    public ProfileDto(Long profileNo, String profileName, LocalDateTime regDate, Boolean isPush) {
        this.profileNo = profileNo;
        this.profileName = profileName;
        this.regDate = regDate;
        this.isPush = isPush;
    }
}
