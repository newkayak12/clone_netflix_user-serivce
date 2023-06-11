package com.netflix_clone.userservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.components.enums.FileType;
import com.netflix_clone.userservice.components.exceptions.BecauseOf;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.FileRequest;
import com.netflix_clone.userservice.repository.dto.reference.FileRequests;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.repository.dto.request.TicketSaveRequest;
import com.netflix_clone.userservice.repository.ticketRepostiory.TicketRepository;
import feign.FeignException;
import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.Arrays;
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
}
