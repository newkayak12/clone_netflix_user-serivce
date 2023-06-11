package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.repository.dto.request.TicketSaveRequest;
import com.netflix_clone.userservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService service;


    @GetMapping(value = "/")
    public ResponseEntity<List<TicketDto>> tickets() {
        return new ResponseEntity<List<TicketDto>>(service.tickets(), HttpStatus.OK);
    }

    @GetMapping(value = "/{ticketNo}")
    public ResponseEntity<TicketDto> ticket(@PathVariable Long ticketNo) throws CommonException {
        return new ResponseEntity(service.ticket(ticketNo),HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> save(@ModelAttribute TicketSaveRequest ticketSaveRequest) throws CommonException {
        return new ResponseEntity<>(service.save(ticketSaveRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ticketNo}")
    public ResponseEntity<Boolean> remove(@PathVariable Long ticketNo){
        return new ResponseEntity<>(service.remove(ticketNo), HttpStatus.OK);
    }

}
