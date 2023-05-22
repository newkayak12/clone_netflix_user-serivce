package com.netflix_clone.userservice.repository.ticketRepostiory;

import com.netflix_clone.userservice.repository.dto.reference.TicketDto;

import java.util.List;

public interface TicketRepositoryCustom {
    List<TicketDto> tickets();
    TicketDto ticket(Long ticketNo);
}
