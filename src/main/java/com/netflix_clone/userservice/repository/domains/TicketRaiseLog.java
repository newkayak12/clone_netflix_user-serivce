package com.netflix_clone.userservice.repository.domains;

import javax.persistence.*;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "TicketRaiseLog")
@Entity
public class TicketRaiseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raiseLog")
    private Long raiseLog;

    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private Account account;

    @OneToOne
    @JoinColumn(name = "payNo")
    private TicketPaymentLog ticketPaymentLog;


}
