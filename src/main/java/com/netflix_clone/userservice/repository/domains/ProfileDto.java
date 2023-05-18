package com.netflix_clone.userservice.repository.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String profileName;
    private LocalDateTime regDate;
    private Boolean isPush;
    private LocalDateTime lastSignInDate;
}
