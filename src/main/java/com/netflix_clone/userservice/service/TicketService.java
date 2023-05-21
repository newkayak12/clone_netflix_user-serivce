package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.configure.feign.ImageFeign;
import com.netflix_clone.userservice.enums.FileType;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.repository.ticketRepostiory.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TicketService {
    private final TicketRepository repository;
    private final ImageFeign imageFeign;
    public List<TicketDto> tickets() {
        List<TicketDto> tickets = repository.tickets();
        return tickets.stream().peek( ticket -> {
            FileDto image = imageFeign.files(ticket.getTicketNo(), FileType.TICKET)
                                      .getBody().stream().findAny().orElseGet(() -> null);
            ticket.setImage(image);
        }).collect(Collectors.toList());
    }
}
