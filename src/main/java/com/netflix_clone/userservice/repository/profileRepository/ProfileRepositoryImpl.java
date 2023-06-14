package com.netflix_clone.userservice.repository.profileRepository;

import com.netflix_clone.userservice.repository.domains.Profile;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.reference.QProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileModifyRequest;
import com.netflix_clone.userservice.repository.dto.request.ProfileRequest;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.netflix_clone.userservice.repository.domains.QProfile.profile;
/**
 * Created on 2023-05-19
 * Project user-service
 */
public class ProfileRepositoryImpl extends QuerydslRepositorySupport implements ProfileRepositoryCustom {
    private JPQLQueryFactory query;

    @Autowired
    public ProfileRepositoryImpl(JPQLQueryFactory query) {
        super(Profile.class);
        this.query = query;
    }

    @Override
    public ProfileDto findByProfileNo(Long profileNo) {
        return query
                .select(new QProfileDto(
                        profile.profileNo,
                        profile.profileName,
                        profile.regDate,
                        profile.isPush
                ))
                .from(profile)
                .where(profile.profileNo.eq(profileNo))
                .fetchOne();
    }

    @Override
    public List<ProfileDto> profiles(ProfileRequest profileRequest) {
        return query
                .select(new QProfileDto(
                        profile.profileNo,
                        profile.profileName,
                        profile.regDate,
                        profile.isPush
                ))
                .from(profile)
                .where(profile.account.userNo.eq(profileRequest.getUserNo()))
                .fetch();
    }

    @Override
    public Boolean isChangeProfileName(ProfileModifyRequest profileNameRequest) {
        return query.update(profile)
                    .set(profile.profileName, profileNameRequest.getProfileName())
                    .where(profile.profileNo.eq(profileNameRequest.getProfileNo()))
                    .execute() >= 1;
    }

    @Override
    public Boolean isChangePushState(ProfileModifyRequest profileNameRequest) {
        return query.update(profile)
                .set(profile.isPush, profileNameRequest.getIsPush())
                .where(profile.profileNo.eq(profileNameRequest.getProfileNo()))
                .execute() >= 1;
    }

    @Override
    public Boolean removeProfile(Long profileNo) {
        return query.delete(profile).where(profile.profileNo.eq(profileNo)).execute() > 0;
    }
}
