package com.netflix_clone.userservice.repository.payLogRepository;

import com.netflix_clone.userservice.repository.domains.TicketPaymentLog;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PayLogRepositoryImpl extends QuerydslRepositorySupport implements PayLogRepositoryCustom {
    private JPQLQueryFactory query;

    public PayLogRepositoryImpl(JPQLQueryFactory query) {
        super(TicketPaymentLog.class);
        this.query = query;
    }
}
