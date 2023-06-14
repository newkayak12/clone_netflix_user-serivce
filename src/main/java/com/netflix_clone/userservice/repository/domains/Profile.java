package com.netflix_clone.userservice.repository.domains;

import lombok.ToString;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;


import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "profile")
@Entity
@DynamicInsert
@DynamicUpdate
@ToString
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
    @ColumnDefault(value = "TRUE")
    private Boolean isPush;
    @Column(name = "lastSignInDate", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    @ColumnDefault(value = "CURRENT_TIMESTAMP()")
    private LocalDateTime lastSignInDate;



    @PrePersist
    public void setSignDate(){
        this.regDate = LocalDateTime.now();
    }
    @PostLoad
    public void renewLastSignInDate() {
        this.lastSignInDate = LocalDateTime.now();
    }
}
