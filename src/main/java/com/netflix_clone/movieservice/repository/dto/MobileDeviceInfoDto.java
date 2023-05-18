package com.netflix_clone.userservice.repository.dto;

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
public class MobileDeviceInfoDto implements Serializable {
    private ProfileIdDto profileId;
    private String deviceType;
    private String pushKey;
    private String uuid;
    private String osVersion;
}
