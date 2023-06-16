package com.netflix_clone.userservice.repository.ticketRaiseRepository;

import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import com.netflix_clone.userservice.repository.dto.reference.PageableRequest;
import com.netflix_clone.userservice.repository.dto.reference.TicketRaiseLogDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TicketRaiseRepositoryCustom {

    TicketRaiseLogDto ticketInfoByUserNo(Long userNo, LocalDate now);
    PageImpl<TicketRaiseLogDto> raises(Pageable pageable, PageableRequest request);
}
