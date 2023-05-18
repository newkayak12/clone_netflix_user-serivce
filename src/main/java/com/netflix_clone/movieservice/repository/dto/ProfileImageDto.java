package com.netflix_clone.userservice.repository.dto;

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
public class ProfileImageDto implements Serializable {
    private ProfileIdDto profile;
    private String url;
    private LocalDateTime regDate;
}
