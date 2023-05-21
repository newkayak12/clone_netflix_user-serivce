package com.netflix_clone.userservice.repository.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModifyRequest {
    private Long profileNo;
    private String profileName;
    private Boolean isPush;


}
