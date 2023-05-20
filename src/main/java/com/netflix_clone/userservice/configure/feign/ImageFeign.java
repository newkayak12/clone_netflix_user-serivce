package com.netflix_clone.userservice.configure.feign;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created on 2023-05-12
 * Project user-service
 */
@Qualifier(value = "image")
@FeignClient(name = "netflix-clone-image-service")
public interface ImageFeign {


}
