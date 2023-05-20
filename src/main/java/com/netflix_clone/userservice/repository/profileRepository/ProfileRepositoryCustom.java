package com.netflix_clone.userservice.repository.profileRepository;

import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;

import java.util.List;

public interface ProfileRepositoryCustom {
    List<ProfileDto> profiles(ProfileRequest profileRequest);
}
