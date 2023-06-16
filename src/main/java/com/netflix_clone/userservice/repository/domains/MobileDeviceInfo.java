package com.netflix_clone.userservice.repository.domains;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "mobileDeviceInfo")
@Entity
@DynamicInsert
@DynamicUpdate
public class MobileDeviceInfo implements Serializable {

    @EmbeddedId
    private ProfileId profileId;
    @Column(name = "deviceType", columnDefinition = "VARCHAR(10)")
    private String deviceType;
    @Column(name = "pushKey", columnDefinition = "VARCHAR(500)")
    private String pushKey;
    @Column(name = "uuid", columnDefinition = "VARCHAR(500)")
    private String uuid;
    @Column(name = "osVersion", columnDefinition = "VARCHAR(100)")
    private String osVersion;


}
