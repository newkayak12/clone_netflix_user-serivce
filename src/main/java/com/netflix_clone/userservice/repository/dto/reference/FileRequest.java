package com.netflix_clone.userservice.repository.dto.reference;

import com.netflix_clone.userservice.components.enums.FileType;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created on 2023-05-19
 * Project file-service
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class FileRequest {
    private Long fileNo;
    private Long tableNo;
    private String fileType;
    private String storedFileName;
    private String originalFileName;
    private Integer orders;
    private String contentType;
    private Long fileSize;
    private MultipartFile rawFile;

}
