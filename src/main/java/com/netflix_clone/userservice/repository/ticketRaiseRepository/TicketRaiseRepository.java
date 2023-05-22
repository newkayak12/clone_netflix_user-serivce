package com.netflix_clone.userservice.repository.ticketRaiseRepository;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRaiseRepository extends JpaRepository<TicketRaiseLog, Long>, TicketRaiseRepositoryCustom {
}
