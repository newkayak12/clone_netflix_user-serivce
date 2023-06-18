package com.netflix_clone.userservice.repository.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Table(name = "TicketRaiseLog")
@Entity
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(value = {"account"}, allowGetters = false)
public class TicketRaiseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "raiseLogNo")
    private Long raiseLogNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketNo")
    private Ticket ticket;
    @ManyToOne(fetch = FetchType.LAZY)
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


    public void toggleSubscribeStatus() {
        if(Objects.nonNull(this.subscribeNext)) this.subscribeNext = !this.subscribeNext;
    }


}
