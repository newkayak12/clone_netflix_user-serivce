package com.netflix_clone.userservice.repository.deviceRepository;

import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;

import java.util.Optional;

public interface DeviceRepositoryCustom {
    MobileDeviceInfoDto findByProfileNo(Long profileNo);
}
