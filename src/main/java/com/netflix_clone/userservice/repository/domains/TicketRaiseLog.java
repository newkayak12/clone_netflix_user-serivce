package com.netflix_clone.userservice.repository.domains;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "TicketRaiseLog")
@Entity
@DynamicInsert
@DynamicUpdate
public class TicketRaiseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raiseLogNo")
    private Long raiseLogNo;
    @ManyToOne
    @JoinColumn(name = "ticketNo")
    private Ticket ticket;
    @ManyToOne
    @JoinColumn(name = "userNo")
    private Account account;
    @Column(name = "startDate", columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(name = "endDate", columnDefinition = "DATE")
    private LocalDate endDate;
    @Column(name = "isActive", columnDefinition = "BIT(1)")
    private Boolean isActive;
    @Column(name = "subscribeNext", columnDefinition = "BIT(1) default TRUE")
    private Boolean subscribeNext;



}
