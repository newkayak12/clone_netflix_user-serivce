package com.netflix_clone.userservice.repository.dto.request;

import com.netflix_clone.userservice.repository.domains.Ticket;
import com.netflix_clone.userservice.repository.dto.reference.TicketDto;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
public class TicketSaveRequest extends TicketDto {
    private MultipartFile rawFile;
}
