package com.netflix_clone.userservice.repository.deviceRepository;

import com.netflix_clone.userservice.repository.domains.MobileDeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<MobileDeviceInfo, Long>, DeviceRepositoryCustom{
}
