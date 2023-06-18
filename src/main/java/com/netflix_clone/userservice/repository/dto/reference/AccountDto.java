package com.netflix_clone.userservice.repository.dto.reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netflix_clone.userservice.components.validations.AccountValid;
import com.netflix_clone.userservice.components.validations.ProfileValid;
import com.netflix_clone.userservice.components.validations.TicketValid;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = {"lastSignDate", "adultCheckDate", "userPwd"}, allowSetters = false)
@Getter
@Setter
public class AccountDto implements Serializable {
    @NotEmpty(message = "계정 정보가 필요합니다.", groups = {ProfileValid.Save.class, TicketValid.Raise.class})
    private Long userNo;

    @NotEmpty(message = "아이디를 입력하세요.", groups = {AccountValid.SignUp.class, AccountValid.SignIn.class, AccountValid.FindPwd.class})
    private String userId;
    @NotEmpty(message = "비밀번호를 입력하세요.", groups = {
                                                        AccountValid.SignUp.class,
                                                        AccountValid.SignIn.class,
                                                        AccountValid.changePwd.class
                                                     })
    private String userPwd;
    private LocalDateTime regDate;
    private Boolean isAdult;
    private LocalDateTime adultCheckDate;

    @NotEmpty(message = "전화번호를 입력하세요.", groups = {AccountValid.SignUp.class, AccountValid.FindId.class, AccountValid.FindPwd.class})
    private String mobileNo;
    @NotEmpty(message = "이메일 입력하세요.", groups = {AccountValid.SignUp.class, AccountValid.FindId.class, AccountValid.FindPwd.class})
    @Email(message = "이메일 형식에 맞지 않습니다.", groups = {AccountValid.SignUp.class})
    private String email;
    private Boolean isSubscribed;
    private LocalDateTime lastSignDate;

    private List<ProfileDto> profiles = new ArrayList<>();
    @Transient
    private TicketRaiseLogDto ticketStatus;

    public void setTicketStatus(TicketRaiseLogDto ticketStatus){
        if(Objects.nonNull(ticketStatus)){
            this.ticketStatus = ticketStatus;
            this.isSubscribed = ticketStatus.getIsActive();
        } else {
            this.isSubscribed = false;
        }

    }

    @QueryProjection
    public AccountDto(Long userNo, String userId, String userPwd, LocalDateTime regDate, Boolean isAdult, LocalDateTime adultCheckDate, String mobileNo, String email, Boolean isSubscribed, LocalDateTime lastSignDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPwd = userPwd;
        this.regDate = regDate;
        this.isAdult = isAdult;
        this.adultCheckDate = adultCheckDate;
        this.mobileNo = mobileNo;
        this.email = email;
        this.isSubscribed = isSubscribed;
        this.lastSignDate = lastSignDate;
    }
}
