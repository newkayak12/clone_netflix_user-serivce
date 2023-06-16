package com.netflix_clone.userservice.repository.dto.reference;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketPaymentLogDto implements Serializable {
    private Long payNo;
    private AccountDto account;
    private TicketDto ticket;
    private TicketRaiseLogDto raiseLog;
    private String originalTransaction;
    private LocalDateTime payDay;
}
