package com.netflix_clone.userservice.repository.userRepository;

import com.netflix_clone.userservice.repository.domains.Account;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.QAccountDto;
import com.netflix_clone.userservice.repository.dto.request.ChangePasswordRequest;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.netflix_clone.userservice.repository.domains.QAccount.account;
import static com.netflix_clone.userservice.repository.domains.QProfile.profile;
/**
 * Created on 2023-05-18
 * Project user-service
 */
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    private JPQLQueryFactory query;

    @Autowired
    public UserRepositoryImpl(JPQLQueryFactory query) {
        super(Account.class);
        this.query = query;
    }

    @Override

    public AccountDto signIn(SignInRequest accountDto) {
        return query
                .select(new QAccountDto(
                        account.userNo,
                        account.userId,
                        account.userPwd,
                        account.regDate,
                        account.isAdult,
                        account.adultCheckDate,
                        account.mobileNo,
                        account.email,
                        account.isSubscribed,
                        account.lastSignDate
                ))
                .from(account)
                .leftJoin(profile)
                .where(account.userId.eq(accountDto.getUserId()))
                .fetchOne();
    }

    @Override
    public Boolean changePassword(ChangePasswordRequest changePasswordRequest) {
        return query
                .update(account)
                .set(account.userPwd, changePasswordRequest.getNewUserPwd())
                .where(account.userNo.eq(changePasswordRequest.getUserNo()))
                .execute() > 0;
    }


}
