package com.netflix_clone.userservice.repository.dto.reference;

import com.netflix_clone.userservice.enums.Resolution;
import com.netflix_clone.userservice.enums.TicketType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
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

    @Transient
    private FileDto image;

    @QueryProjection
    public TicketDto(Long ticketNo, String name, TicketType type, Integer watchableSimultaneously, Resolution maximumResolution, Boolean isSupportHDR, Integer savableCount) {
        this.ticketNo = ticketNo;
        this.name = name;
        this.type = type;
        this.watchableSimultaneously = watchableSimultaneously;
        this.maximumResolution = maximumResolution;
        this.isSupportHDR = isSupportHDR;
        this.savableCount = savableCount;
    }
}
