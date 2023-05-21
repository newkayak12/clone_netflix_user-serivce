package com.netflix_clone.userservice.repository.dto.reference;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"deviceType", "uuid", "pushKey"})
public class MobileDeviceInfoDto implements Serializable {
    private Long profileId;
    private String deviceType;
    private String pushKey;
    private String uuid;
    private String osVersion;

    @QueryProjection
    public MobileDeviceInfoDto(Long profileId, String deviceType, String pushKey, String uuid, String osVersion) {
        this.profileId = profileId;
        this.deviceType = deviceType;
        this.pushKey = pushKey;
        this.uuid = uuid;
        this.osVersion = osVersion;
    }
}
