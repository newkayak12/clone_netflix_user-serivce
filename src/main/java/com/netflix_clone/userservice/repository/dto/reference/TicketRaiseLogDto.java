package com.netflix_clone.userservice.repository.dto.reference;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"account"})
@JsonInclude( value = JsonInclude.Include.NON_EMPTY )
public class TicketRaiseLogDto implements Serializable {
    private Long raiseLogNo;
    private TicketDto ticket;
    private AccountDto account;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Boolean subscribeNext;

    @QueryProjection
    public TicketRaiseLogDto(Long raiseLogNo, TicketDto ticket, LocalDate startDate, LocalDate endDate, Boolean isActive) {
        this.raiseLogNo = raiseLogNo;
        this.ticket = ticket;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    @QueryProjection
    public TicketRaiseLogDto(Long raiseLogNo, TicketDto ticket, LocalDate startDate, LocalDate endDate, Boolean isActive, Boolean subscribeNext) {
        this.raiseLogNo = raiseLogNo;
        this.ticket = ticket;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.subscribeNext = subscribeNext;
    }
    @QueryProjection
    public TicketRaiseLogDto(Long raiseLogNo, TicketDto ticket) {
        this.raiseLogNo = raiseLogNo;
        this.ticket = ticket;
    }
    @QueryProjection
    public TicketRaiseLogDto(Long raiseLogNo, TicketDto ticket, AccountDto account) {
        this.raiseLogNo = raiseLogNo;
        this.ticket = ticket;
        this.account = account;
    }

    public void init(LocalDate payDay){
        this.startDate = payDay;
        this.endDate = payDay.plusMonths(1);
        this.isActive = true;
    }

}
