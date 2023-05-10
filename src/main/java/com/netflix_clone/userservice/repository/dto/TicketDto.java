package com.netflix_clone.userservice.repository.dto;

import com.netflix_clone.userservice.component.enums.Resolution;
import com.netflix_clone.userservice.component.enums.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto implements Serializable {
    private Long ticketNo;
    private String name;
    private TicketType type;
    private Integer watchableSimultaneously;
    private Resolution maximumResolution;
    private Boolean isSupportHDR;
    private Integer savableCount;
}
