package com.netflix_clone.userservice.repository.dto.request;

import com.netflix_clone.userservice.components.validations.TicketValid;
import com.netflix_clone.userservice.repository.domains.Account;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.AccountDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public class TicketRaiseRequest {
    private AccountDto account;
    private TicketDto ticket;
    private LocalDate payDay = LocalDate.now();
    @NotEmpty(message = "결제 정보가 비었습니다.", groups = {TicketValid.Raise.class})
    private String originalTransaction;
}
