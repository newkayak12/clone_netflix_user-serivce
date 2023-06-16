package com.netflix_clone.userservice.repository.ticketPaymentLogRepository;

import com.netflix_clone.userservice.repository.domains.TicketPaymentLog;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TicketPaymentLogRepositoryImpl extends QuerydslRepositorySupport implements TicketPaymentLogRepositoryCustom {
    private JPQLQueryFactory query;

    @Autowired
    public TicketPaymentLogRepositoryImpl(JPQLQueryFactory query) {
        super(TicketPaymentLog.class);
        this.query = query;
    }

}
