package com.netflix_clone.userservice.repository.domains;


import com.netflix_clone.userservice.components.enums.Resolution;
import com.netflix_clone.userservice.components.enums.TicketType;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "ticket")
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticketNo", columnDefinition = "BIGINT(20)")
    private Long ticketNo;
    @Column(name = "name", columnDefinition = "VARCHAR(200)")
    private String name;
    @Column(name = "type", columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private TicketType type;
    @Column(name = "watchableSimultaneously", columnDefinition = "INT(11)")
    private Integer watchableSimultaneously;
    @Column(name = "maximumResolution", columnDefinition = "VARCHAR(100)")
    @Enumerated(EnumType.STRING)
    private Resolution maximumResolution;
    @Column(name = "isSupportHDR", columnDefinition = "BIT(1)")
    private Boolean isSupportHDR;
    @Column(name = "savableCount", columnDefinition = "INT(11)")
    private Integer savableCount;
    @Column(name = "price", columnDefinition = "DECIMAL(19)")
    private BigInteger price;
}
