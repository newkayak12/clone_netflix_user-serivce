package com.netflix_clone.userservice.repository.dto.request;

import com.netflix_clone.userservice.repository.domains.ProfileDto;
import lombok.*;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest  {
    private Long userNo;
}
