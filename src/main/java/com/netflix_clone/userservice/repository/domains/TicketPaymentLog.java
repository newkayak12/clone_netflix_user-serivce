package com.netflix_clone.userservice.repository.domains;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "ticketPaymentLog")
@Entity
@DynamicInsert
@DynamicUpdate
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
    @OneToOne
    @JoinColumn(name = "raiseLogNo")
    private TicketRaiseLog raiseLog;
    @Column(name = "originalTransaction", columnDefinition = "VARCHAR(500)")
    private String originalTransaction;
    @Column(name = "dayDay", columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    @ColumnDefault(value = "CURRENT_TIMESTAMP()")
    private LocalDateTime payDay;
}
