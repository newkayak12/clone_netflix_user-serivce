package com.netflix_clone.userservice.repository.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "profileImage")
@Entity
public class ProfileImage implements Serializable {
    @EmbeddedId
    private ProfileId profile;
    @Column(name = "url", nullable = true, columnDefinition = "VARCHAR(500)")
    private String url;
    @Column(name = "regDate", columnDefinition = "DATETIME")
    private LocalDateTime regDate;

}
