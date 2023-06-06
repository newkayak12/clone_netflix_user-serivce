package com.netflix_clone.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix_clone.userservice.components.configure.feign.ImageFeign;
import com.netflix_clone.userservice.repository.dto.reference.FileDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.Mockito.*;
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles(value = {"local"})
public class TicketControllerTest {

    private final String prefix = "/api/v1/ticket";
    private final String tickets = prefix + "/";
    private final String ticket = prefix + "/";
    private final String save = prefix + "/save";

    @Mock
    private ImageFeign imageFeign;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Nested
    @DisplayName("티켓 등록 테스트")
    @Transactional
    @Rollback(value = true)
    public class TicketSaveTest {
        @Test
        @DisplayName(value = "성공")
        public void success () throws Exception {
            InputStream inputStream = new FileInputStream("/Users/sanghyeonkim/Downloads/R1280x0.png");
            MockMultipartFile file = new MockMultipartFile("file.png", "file.png", "png", inputStream);


            //when
            when(imageFeign.save(anyList()))
                    .thenReturn(new ResponseEntity<>(Arrays.asList(new FileDto()), HttpStatus.OK));


            mockMvc.perform(
                        multipart(save)
                        .file(file)
                        .queryParam("name", "BASIC")
                        .queryParam("type", "BASIC")
                        .queryParam("watchableSimultaneously", "1")
                        .queryParam("maximumResolution", "HD")
                        .queryParam("isSupportHDR", "false")
                        .queryParam("savableCount", "10")
                        .queryParam("price", "9900")
                        .contentType(MediaType.MULTIPART_FORM_DATA)

                    )
            .andDo(print());
        }
    }
}
