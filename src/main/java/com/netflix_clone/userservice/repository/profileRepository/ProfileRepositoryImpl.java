package com.netflix_clone.userservice.repository.profileRepository;

import com.netflix_clone.userservice.repository.domains.Profile;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.reference.QAccountDto;
import com.netflix_clone.userservice.repository.dto.reference.QProfileDto;
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
}
