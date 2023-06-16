package com.netflix_clone.userservice.repository.ticketRaiseRepository;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import com.netflix_clone.userservice.repository.dto.reference.*;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public PageImpl<TicketRaiseLogDto> raises(Pageable pageable, PageableRequest request) {
        List<TicketRaiseLogDto> list = query.select(
                new QTicketRaiseLogDto(
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
                )
        )
        .from(ticketRaiseLog)
        .leftJoin(ticketRaiseLog.ticket, ticket)
        .fetchJoin()
        .where(ticketRaiseLog.account.userNo.eq(request.getTableNo()))
        .orderBy(ticketRaiseLog.startDate.desc())
        .limit(pageable.getPageSize())
        .offset(pageable.getOffset())
        .fetch();

        Long count = query.from(ticketRaiseLog)
                          .where(ticketRaiseLog.account.userNo.eq(request.getTableNo()))
                          .fetchCount();
        return new PageImpl<TicketRaiseLogDto>(list, pageable, count);
    }
}
