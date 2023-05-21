package com.netflix_clone.userservice.repository.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImageRequest {
    private Long profileNo;
    private MultipartFile rawFile;
}
