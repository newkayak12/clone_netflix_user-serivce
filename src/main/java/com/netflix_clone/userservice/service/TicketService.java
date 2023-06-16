package com.netflix_clone.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.components.enums.FileType;
import com.netflix_clone.userservice.components.exceptions.BecauseOf;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.domains.TicketPaymentLog;
import com.netflix_clone.userservice.repository.domains.TicketRaiseLog;
import com.netflix_clone.userservice.repository.dto.reference.*;
import com.netflix_clone.userservice.repository.dto.request.TicketRaiseRequest;
import com.netflix_clone.userservice.repository.dto.request.TicketSaveRequest;
import com.netflix_clone.userservice.repository.ticketPaymentLogRepository.TicketPaymentLogRepository;
import com.netflix_clone.userservice.repository.ticketRaiseRepository.TicketRaiseRepository;
import com.netflix_clone.userservice.repository.ticketRepostiory.TicketRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TicketService {
    private final TicketRepository repository;
    private final TicketPaymentLogRepository paymentLogRepository;
    private final TicketRaiseRepository raiseRepository;
    private final ImageFeign imageFeign;
    private final ModelMapper mapper;
    private final ObjectMapper objectMapper;

    @Transactional(readOnly = true)
    public List<TicketDto> tickets() {
        List<TicketDto> tickets = repository.tickets();
        return tickets.stream().peek( ticket -> {
            FileDto image = imageFeign.files(ticket.getTicketNo(), FileType.TICKET)
                                      .getBody().stream().findAny().orElseGet(() -> null);
            ticket.setImage(image);
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TicketDto ticket(Long ticketNo) throws CommonException {
        return Optional.ofNullable(repository.ticket(ticketNo)).map( ticket -> {
            FileDto image = imageFeign.files(ticket.getTicketNo(), FileType.TICKET)
                                      .getBody().stream().findAny().orElseGet(() -> null);
            ticket.setImage(image);
            return ticket;
        }).orElseThrow(() -> new CommonException(BecauseOf.NO_DATA));
    }

    public Boolean save(TicketSaveRequest ticketSaveRequest) throws CommonException {

        try {
            if (Objects.nonNull(ticketSaveRequest.getTicketNo()))
                imageFeign.remove(ticketSaveRequest.getTicketNo(), FileType.TICKET);
        } catch (FeignException exception) {
            exception.printStackTrace();
        }

        Long ticketNo =  Optional.ofNullable(repository.save(mapper.map(ticketSaveRequest, Ticket.class)))
                                        .map(Ticket::getTicketNo)
                                        .orElseThrow(() -> new CommonException(BecauseOf.INSERT_FAILURE));

        MultipartFile rawFile = ticketSaveRequest.getRawFile();
        FileRequest request = new FileRequest();
        request.setRawFile(rawFile);
        request.setTableNo(ticketNo);
        request.setFileType(FileType.TICKET.name());


        return Objects.nonNull(imageFeign.save(request).getBody());
    }

    public Boolean remove(Long ticketNo) {
        try {
            if (Objects.nonNull(ticketNo))
                imageFeign.remove(ticketNo, FileType.TICKET);
        } catch (FeignException exception) {
            exception.printStackTrace();
        }

        return repository.remove(ticketNo);
    }

    public TicketDto raiseTicket(TicketRaiseRequest request) throws CommonException {
        TicketRaiseLogDto raiseLogDto = mapper.map(request, TicketRaiseLogDto.class);
        raiseLogDto.init(request.getPayDay());
        raiseLogDto = mapper.map(
                                     raiseRepository.save(mapper.map(raiseLogDto, TicketRaiseLog.class)),
                                     TicketRaiseLogDto.class
                                );
        TicketPaymentLogDto dto = mapper.map(request, TicketPaymentLogDto.class);
        dto.setRaiseLog(raiseLogDto);
        TicketPaymentLog paymentLog = paymentLogRepository.save(mapper.map(dto, TicketPaymentLog.class));
        if(Objects.isNull(paymentLog)) throw new CommonException(BecauseOf.PAYMENT_FAILURE);
        return request.getTicket();
    }

    @Transactional(readOnly = true)
    public PageImpl<TicketRaiseLogDto> raises(PageableRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getLimit());
        return raiseRepository.raises(pageable, request);
    }
}
