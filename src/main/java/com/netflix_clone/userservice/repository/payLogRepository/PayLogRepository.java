package com.netflix_clone.userservice.repository.payLogRepository;

import com.netflix_clone.userservice.repository.domains.TicketPaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayLogRepository extends JpaRepository<TicketPaymentLog, Long>, PayLogRepositoryCustom {
}
