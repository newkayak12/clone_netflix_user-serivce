package com.netflix_clone.userservice.repository.ticketPaymentLogRepository;

import com.netflix_clone.userservice.repository.domains.TicketPaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPaymentLogRepository extends JpaRepository<TicketPaymentLog, Long>, TicketPaymentLogRepositoryCustom {
}
