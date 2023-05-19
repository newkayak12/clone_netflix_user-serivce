package com.netflix_clone.userservice.service;

import com.netflix_clone.userservice.enums.FileType;
import lombok.Data;
import lombok.ToString;

/**
 * Created on 2023-05-19
 * Project user-service
 */
@Data
@ToString
public class ImageDto {
    private Long imageNo;
    private FileType fileType;
    private Long tableNo;
    private String url;
}
