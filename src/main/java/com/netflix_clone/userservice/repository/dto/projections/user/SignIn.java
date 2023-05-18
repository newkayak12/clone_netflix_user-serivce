package com.netflix_clone.userservice.repository.dto.projections.user;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A Projection for the {@link com.netflix_clone.userservice.repository.domains.Account} entity
 */
public interface SignIn {
    Long getUserNo();

    String getUserId();

    String getUserPwd();

    LocalDateTime getRegDate();

    Boolean isIsAdult();

    LocalDateTime getAdultCheckDate();

    String getMobileNo();

    String getEmail();

    Boolean isIsSubscribed();

    List<ProfileInfo> getProfiles();

    /**
     * A Projection for the {@link com.netflix_clone.userservice.repository.domains.Profile} entity
     */
    interface ProfileInfo {
        Long getProfileNo();

        String getProfileName();

        LocalDateTime getRegDate();

        Boolean isIsPush();

        LocalDateTime getLastSignInDate();
    }
}