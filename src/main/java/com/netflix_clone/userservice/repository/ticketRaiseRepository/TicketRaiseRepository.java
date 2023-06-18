package com.netflix_clone.userservice.repository.ticketRaiseRepository;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRaiseRepository extends JpaRepository<TicketRaiseLog, Long>, TicketRaiseRepositoryCustom {
    @EntityGraph(attributePaths = {"ticket"})
    Optional<TicketRaiseLog> findTopByAccountUserNoOrderByRaiseLogNoDesc( Long userNo );
}

