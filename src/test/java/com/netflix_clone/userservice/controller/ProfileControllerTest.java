package com.netflix_clone.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix_clone.userservice.repository.dto.reference.ProfileDto;
import com.netflix_clone.userservice.repository.dto.request.ProfileSaveRequest;
import com.netflix_clone.userservice.util.AbstractControllerTest;
import com.netflix_clone.userservice.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@EnableConfigurationProperties
@ActiveProfiles(value = {"local"})
public class ProfileControllerTest extends AbstractControllerTest {
    private final String prefix = "/api/v1/profile";
    @Spy
    private ModelMapper mapper;
    @Spy
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName(value = "프로필 리스트")
    class Profiles {


        @Test
        @DisplayName(value = "리스트 없음")
        public void empty() throws Exception {
            mockMvc.perform(
                    get(prefix+"/")
                    .param("userNo","23")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
        }
    }

    @Nested
    @DisplayName(value = "프로필 생성")
    public class SaveProfile {
        @Test
        @DisplayName(value = "성공")
        public void success() throws Exception {
            Map<String,Object> param = Map.of(
                    "profileName", "test1",
                    "account", Map.of("userNo",  22L)
            );

            ProfileSaveRequest request = mapper.map(param, ProfileSaveRequest.class);

            mockMvc.perform(
                multipart(prefix+"/save")
                .file(TestUtil.getMockMultiPartFile("/Users/sanghyeonkim/Downloads/R1280x0.png", "image", "rawFile"))
                .param("profileName", "test1")
                .param("account.userNo", "22")
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$").isNotEmpty())
            .andExpect(jsonPath("$.profileName").value("test1"))
            .andExpect(jsonPath("$.isPush").value("true"))
            .andExpect(jsonPath("$.image").isNotEmpty());

        }
    }
}
