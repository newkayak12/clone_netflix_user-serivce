package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.components.validations.TicketValid;
import com.netflix_clone.userservice.repository.dto.reference.PageableRequest;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.repository.dto.reference.TicketRaiseLogDto;
import com.netflix_clone.userservice.repository.dto.request.TicketRaiseRequest;
import com.netflix_clone.userservice.repository.dto.request.TicketSaveRequest;
import com.netflix_clone.userservice.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(value = "/{ticketNo:[\\d]+}")
    public ResponseEntity<TicketDto> ticket(@PathVariable Long ticketNo) throws CommonException {
        return new ResponseEntity(service.ticket(ticketNo),HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Boolean> save(@ModelAttribute @Valid @Validated(value = {TicketValid.SaveTicket.class})
                                        TicketSaveRequest ticketSaveRequest) throws CommonException {
        return new ResponseEntity<>(service.save(ticketSaveRequest), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ticketNo:[\\d]+}")
    public ResponseEntity<Boolean> remove(@PathVariable Long ticketNo){
        return new ResponseEntity<>(service.remove(ticketNo), HttpStatus.OK);
    }

    @PostMapping(value = "/raise/ticket")
    public ResponseEntity<TicketDto> raiseTicket (@RequestBody @Validated(value = {TicketValid.Raise.class})
                                            @Valid TicketRaiseRequest request ) throws CommonException {
       return ResponseEntity.ok(service.raiseTicket(request));
    }

    @GetMapping(value = "/raises")
    public ResponseEntity<PageImpl<TicketRaiseLogDto>> raises(@ModelAttribute PageableRequest request) {
        return ResponseEntity.ok(service.raises(request));
    }
}
