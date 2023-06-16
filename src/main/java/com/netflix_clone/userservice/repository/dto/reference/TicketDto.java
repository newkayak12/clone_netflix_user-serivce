package com.netflix_clone.userservice.repository.dto.reference;

import com.netflix_clone.userservice.components.enums.Resolution;
import com.netflix_clone.userservice.components.enums.TicketType;
import com.netflix_clone.userservice.components.validations.TicketValid;
import com.querydsl.core.annotations.QueryProjection;
import com.sun.mail.imap.protocol.BODY;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created on 2023-05-10
 * Project user-service
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto implements Serializable {
    @NotEmpty(message = "요금제 정보를 입력하세요.", groups = {TicketValid.Raise.class})
    private Long ticketNo;
    @NotEmpty(message = "요금제 이름을 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private String name;
    @NotEmpty(message = "요금제 타입을 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private TicketType type;
    @NotEmpty(message = "동시 시청 가능 인원 수를 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private Integer watchableSimultaneously;
    @NotEmpty(message = "최대 화질을 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private Resolution maximumResolution;
    @NotEmpty(message = "HDR 지원 여부를 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private Boolean isSupportHDR;
    @NotEmpty(message = "저장 가능 횟수를 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private Integer savableCount;
    @NotEmpty(message = "가격을 입력하세요.", groups = {TicketValid.SaveTicket.class})
    private BigInteger price;
    private Boolean isActive;

    @Transient
    private FileDto image;

    @QueryProjection
    public TicketDto(Long ticketNo, String name, TicketType type,
                     Integer watchableSimultaneously, Resolution maximumResolution,
                     Boolean isSupportHDR, Integer savableCount, BigInteger price,
                     Boolean isActive) {
        this.ticketNo = ticketNo;
        this.name = name;
        this.type = type;
        this.watchableSimultaneously = watchableSimultaneously;
        this.maximumResolution = maximumResolution;
        this.isSupportHDR = isSupportHDR;
        this.savableCount = savableCount;
        this.price = price;
        this.isActive = isActive;
    }
}
