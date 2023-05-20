package com.netflix_clone.userservice.repository.user;

import com.netflix_clone.userservice.repository.domains.Account;
import com.netflix_clone.userservice.repository.dto.projections.user.SignIn;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.request.SignInRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static com.netflix_clone.userservice.repository.domains.QAccount.account;
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
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(account.userId.eq(accountDto.getUserId()));

        return query
                .select(Projections.bean(AccountDto.class, ExpressionUtils.toExpression(SignIn.class)))
                .from(account)
                .where(builder)
                .fetchOne();
    }
}
