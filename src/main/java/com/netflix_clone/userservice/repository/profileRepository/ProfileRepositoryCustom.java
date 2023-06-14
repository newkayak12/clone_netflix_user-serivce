package com.netflix_clone.userservice.repository.profileRepository;

import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileModifyRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;

import java.util.List;

public interface ProfileRepositoryCustom {
    ProfileDto findByProfileNo(Long profileNo);
    List<ProfileDto> profiles(ProfileRequest profileRequest);

    Boolean isChangeProfileName (ProfileModifyRequest profileNameRequest);
    Boolean isChangePushState (ProfileModifyRequest profileNameRequest);
    Boolean removeProfile( Long profileNo );
}
