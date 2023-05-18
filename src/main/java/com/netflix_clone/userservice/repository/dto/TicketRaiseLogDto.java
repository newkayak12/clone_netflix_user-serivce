package com.netflix_clone.userservice.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRaiseLogDto implements Serializable {
    private Long raiseLog;
    private TicketDto ticket;
    private AccountDto account;
    private TicketPaymentLogDto ticketPaymentLog;
}
