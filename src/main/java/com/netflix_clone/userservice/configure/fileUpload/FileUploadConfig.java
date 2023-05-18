package com.netflix_clone.userservice.configure.fileUpload;

import com.netflix_clone.userservice.constants.Constants;
import org.newkayak.FileUpload.FileUpload;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration(value = "fileUpload")
@DependsOn(value = "constants")
public class FileUploadConfig {
    private FileUpload fileUpload = new FileUpload(Constants.FILE_PATH, false, 1024L * 10L);
}
