package com.netflix_clone.userservice.components.configure.queryDsl;

import com.netflix_clone.userservice.components.configure.ConfigMsg;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created on 2023-05-18
 * Project user-service
 */
@Configuration(value = "querydsl_configuration")
//@RequiredArgsConstructor
public class Config {

    @PostConstruct
    public void enabled(){
        ConfigMsg.msg("QueryDSL");
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPQLQueryFactory jPQLQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }
}
