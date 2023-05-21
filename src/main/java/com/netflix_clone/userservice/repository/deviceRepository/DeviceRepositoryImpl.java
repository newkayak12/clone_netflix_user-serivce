package com.netflix_clone.userservice.repository.deviceRepository;

import com.netflix_clone.userservice.repository.domains.MobileDeviceInfo;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.MobileDeviceInfoDto;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.reference.QMobileDeviceInfoDto;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.netflix_clone.userservice.repository.domains.QMobileDeviceInfo.mobileDeviceInfo;
/**
 * Created on 2023-05-19
 * Project user-service
 */
public class DeviceRepositoryImpl extends QuerydslRepositorySupport implements DeviceRepositoryCustom{
    private JPQLQueryFactory query;

    @Autowired
    public DeviceRepositoryImpl(JPQLQueryFactory query) {
        super(MobileDeviceInfo.class);
        this.query = query;
    }


    @Override
    public MobileDeviceInfoDto findByProfileNo(Long profileNo) {
        return query
                .select(new QMobileDeviceInfoDto(
                        mobileDeviceInfo.profileId.profile.profileNo,
                        mobileDeviceInfo.deviceType,
                        mobileDeviceInfo.pushKey,
                        mobileDeviceInfo.uuid,
                        mobileDeviceInfo.osVersion
                ))
                .from(mobileDeviceInfo)
                .where(mobileDeviceInfo.profileId.profile.profileNo.eq(profileNo))
                .fetchOne();
    }
}
