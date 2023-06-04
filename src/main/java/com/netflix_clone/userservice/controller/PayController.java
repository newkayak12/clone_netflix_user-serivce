package com.netflix_clone.userservice.controller;

import com.netflix_clone.userservice.components.configure.rabbit.RabbitPublisher;
import com.netflix_clone.userservice.components.enums.Rabbit;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/v1/pay")
@RequiredArgsConstructor
public class PayController {
    private final RabbitPublisher publisher;

    @GetMapping(value = "/")
    public void test() {
        ProfileDto profileDto = new ProfileDto(1L, "TEST", LocalDateTime.now(), true);
        publisher.sendTopic(Rabbit.Topic.USER.getName(), Rabbit.RoutingKey.PROFILE_SAVE.getValue(), profileDto);
    }
}
