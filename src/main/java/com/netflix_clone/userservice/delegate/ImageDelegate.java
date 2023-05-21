package com.netflix_clone.userservice.delegate;

import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "image_delegate")
public class ImageDelegate {
    public FileDto setImage(List<FileDto> images, Long tableNo){
        FileDto result = null;

        for ( FileDto image : images ){
            if (tableNo.equals(image.getTableNo())) result = image;
        }

        return result;
    }
    public List<FileDto> setImages( List<FileDto> images, Long tableNo) {
        return images.stream().filter(image -> image.getTableNo().equals(tableNo)).collect(Collectors.toList());
    }
}
