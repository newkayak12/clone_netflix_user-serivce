package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.components.enums.FileType;
import com.netflix_clone.userservice.components.exceptions.BecauseOf;
import com.netflix_clone.userservice.components.exceptions.CommonException;
import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import com.netflix_clone.userservice.repository.dto.reference.FileRequest;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import com.netflix_clone.userservice.repository.dto.request.TicketSaveRequest;
import com.netflix_clone.userservice.repository.ticketRepostiory.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;
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
        Long ticketNo =  Optional.ofNullable(repository.save(mapper.map(ticketSaveRequest, Ticket.class)))
                                        .map(Ticket::getTicketNo)
                                        .orElseThrow(() -> new CommonException(BecauseOf.INSERT_FAILURE));

        MultipartFile rawFile = ticketSaveRequest.getRawFile();
        FileRequest request = new FileRequest();
        request.setRawFile(rawFile);
        request.setTableNo(ticketNo);
        request.setFileType(FileType.TICKET);

        Integer size = imageFeign.save(Arrays.asList(request)).getBody().size();
        return size > 0;
    }
}
