package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService service;

    @GetMapping(value = "/list")
    public ResponseEntity<List<TicketDto>> tickets() {
        return new ResponseEntity<List<TicketDto>>(service.tickets(), HttpStatus.OK);
    }


}
