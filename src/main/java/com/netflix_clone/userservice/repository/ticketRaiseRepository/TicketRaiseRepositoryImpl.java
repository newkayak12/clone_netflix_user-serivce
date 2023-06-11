package com.netflix_clone.userservice.repository.ticketRaiseRepository;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import com.netflix_clone.userservice.repository.dto.reference.QTicketDto;
import com.netflix_clone.userservice.repository.dto.reference.QTicketRaiseLogDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketRaiseLogDto;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.netflix_clone.userservice.repository.domains.QTicket.ticket;
import static com.netflix_clone.userservice.repository.domains.QTicketRaiseLog.ticketRaiseLog;

public class TicketRaiseRepositoryImpl extends QuerydslRepositorySupport implements TicketRaiseRepositoryCustom {
    private JPQLQueryFactory query;

    public TicketRaiseRepositoryImpl(JPQLQueryFactory query) {
        super(TicketRaiseLog.class);
        this.query = query;
    }

    @Override
    public TicketRaiseLogDto ticketInfoByUserNo(Long userNo, LocalDate now) {
        return query.select(new QTicketRaiseLogDto(
                ticketRaiseLog.raiseLogNo,
                new QTicketDto(
                        ticket.ticketNo,
                        ticket.name,
                        ticket.type,
                        ticket.watchableSimultaneously,
                        ticket.maximumResolution,
                        ticket.isSupportHDR,
                        ticket.savableCount,
                        ticket.price,
                        ticket.isActive
                ),
                ticketRaiseLog.startDate,
                ticketRaiseLog.endDate,
                ticketRaiseLog.isActive
                ))
                .from(ticketRaiseLog)
                .leftJoin(ticket).on(ticket.ticketNo.eq(ticketRaiseLog.ticket.ticketNo))
                .fetchJoin()
                .where(
                         ticketRaiseLog.account.userNo.eq(userNo)
                    .and(ticketRaiseLog.startDate.loe(now))
                    .and(ticketRaiseLog.endDate.goe(now))
                    .and(ticketRaiseLog.isActive.isTrue())
                )
                .fetchOne();
    }
}
