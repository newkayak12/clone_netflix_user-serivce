package com.netflix_clone.userservice.configure.queryDsl;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * Created on 2023-05-18
 * Project user-service
 */
@Configuration(value = "querydsl")
@RequiredArgsConstructor
public class QueryDslConfig {
    private final EntityManager entityManager;

    @Bean
    public JPQLQueryFactory jPQLQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
