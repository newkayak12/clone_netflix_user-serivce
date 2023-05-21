package com.netflix_clone.userservice.repository.ticketRepostiory;

import com.netflix_clone.userservice.repository.domains.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryCustom {
}
