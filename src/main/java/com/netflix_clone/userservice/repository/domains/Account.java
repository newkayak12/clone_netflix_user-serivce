package com.netflix_clone.userservice.repository.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "account")
@Entity
@Getter
@NoArgsConstructor
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
    @Column(name = "isAdult", columnDefinition = "BIT(1) default FALSE")
    @ColumnDefault(value = "false")
    private Boolean isAdult;
    @Column(name = "adultCheckDate", columnDefinition = "DATETIME")
    private LocalDateTime adultCheckDate;
    @Column(name = "mobileNo", columnDefinition = "VARCHAR (20)")
    private String mobileNo;
    @Column(name = "email", columnDefinition = "VARCHAR (50)")
    private String email;
    @Column(name = "isSubscribed", columnDefinition = "BIT(1)")
    private Boolean isSubscribed;
    @Column(name = "lastSignDate", columnDefinition = "DATETIME")
    private LocalDateTime lastSignDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<Profile> profiles = new ArrayList<>();

    @PostLoad
    public void renewSignDate(){
        this.lastSignDate = LocalDateTime.now();
    }

    @PrePersist
    public void signUpDate() {
        this.regDate = LocalDateTime.now();
    }
}
