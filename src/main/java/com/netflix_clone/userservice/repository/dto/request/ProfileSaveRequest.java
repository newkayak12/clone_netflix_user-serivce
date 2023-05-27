package com.netflix_clone.userservice.repository.dto.request;

import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@ToString(callSuper = true)
public class ProfileSaveRequest extends ProfileDto {
    private MultipartFile rawFile;
}
