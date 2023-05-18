package com.netflix_clone.userservice.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "account")
@Entity
public class Account implements Serializable {
    @Id
    @Column(name = "userNo", columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column(name = "userId", columnDefinition = "VARCHAR (100)")
    private String userId;
    @Column(name = "userPwd", columnDefinition = "VARCHAR (500)")
    private String userPwd;
    @Column(name = "regDate", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    private LocalDateTime regDate;
    @Column(name = "isAdult", columnDefinition = "BIT(1)")
    private Boolean isAdult;
    @Column(name = "adultCheckDate", columnDefinition = "DATETIME")
    private LocalDateTime adultCheckDate;
    @Column(name = "mobileNo", columnDefinition = "VARCHAR (20)")
    private String mobileNo;
    @Column(name = "email", columnDefinition = "VARCHAR (50)")
    private String email;
    @Column(name = "isSubscribed", columnDefinition = "BIT(1)")
    private Boolean isSubscribed;

    @OneToMany(mappedBy = "account")
    private List<Profile> profiles = new ArrayList<>();
}
