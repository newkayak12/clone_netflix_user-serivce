package com.netflix_clone.userservice.repository.ticketRepostiory;

import com.netflix_clone.userservice.repository.domains.QTicket;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.QTicketDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.querydsl.jpa.JPQLQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static com.netflix_clone.userservice.repository.domains.QTicket.ticket;

public class TicketRepositoryImpl extends QuerydslRepositorySupport implements TicketRepositoryCustom {
    private JPQLQueryFactory query;

    public TicketRepositoryImpl(JPQLQueryFactory query) {
        super(Ticket.class);
        this.query = query;
    }

    @Override
    public List<TicketDto> tickets() {
        return query.select(
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
                    )
                )
                .from(ticket)
                .where(ticket.isActive.isTrue())
                .fetch();
    }

    @Override
    public TicketDto ticket(Long ticketNo) {
        return query.select(
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
                             )
                     )
                     .from(ticket)
                     .where(
                             ticket.isActive.isTrue()
                         .and(ticket.ticketNo.eq(ticketNo))
                     )
                     .fetchOne();
    }

    @Override
    public Boolean remove(Long ticketNo) {
        return query.update(ticket)
                .set(ticket.isActive, false)
                .where(ticket.ticketNo.eq(ticketNo)).execute() > 0;
    }
}
