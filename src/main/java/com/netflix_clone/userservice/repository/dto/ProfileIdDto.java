package com.netflix_clone.userservice.repository.dto;

import com.netflix_clone.userservice.repository.entities.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileIdDto implements Serializable {
    private ProfileDto profile;
}
