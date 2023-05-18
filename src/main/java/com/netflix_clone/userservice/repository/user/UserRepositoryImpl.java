package com.netflix_clone.userservice.repository.user;

import com.netflix_clone.userservice.repository.domains.Account;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
}
