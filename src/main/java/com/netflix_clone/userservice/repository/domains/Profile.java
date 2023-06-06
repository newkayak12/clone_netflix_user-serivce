package com.netflix_clone.userservice.repository.domains;

import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */

import org.hibernate.annotations.Cache;
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "profile")
@Entity
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileNo", columnDefinition = "BIGINT(20)")
    private Long profileNo;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "accountNo", columnDefinition = "BIGINT(20)", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT))
    private Account account;
    @Column(name = "profileName", columnDefinition = "VARCHAR(50)")
    private String profileName;
    @Column(name = "regDate", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    private LocalDateTime regDate;
    @Column(name = "isPush", columnDefinition = "BIT(1) default TRUE")
    private Boolean isPush;
    @Column(name = "lastSignInDate", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    private LocalDateTime lastSignInDate;



    @PostLoad
    public void renewLastSignInDate() {
        this.lastSignInDate = LocalDateTime.now();
    }
}
