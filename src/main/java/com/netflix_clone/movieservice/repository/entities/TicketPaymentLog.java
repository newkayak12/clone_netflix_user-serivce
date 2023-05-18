package com.netflix_clone.userservice.repository.entities;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "ticketPaymentLog")
@Entity
public class TicketPaymentLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payNo")
    private Long payNo;
    @ManyToOne
    @JoinColumn(name = "userNo", foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT))
    private Account account;
    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Ticket ticket;
    @Column(name = "originalTransaction", columnDefinition = "VARCHAR(500)")
    private String originalTransaction;
    @Column(name = "dayDay", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    private LocalDateTime payDay;
}
