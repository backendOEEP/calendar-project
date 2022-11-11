package com.example.study.calendarproject.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@DisplayName("Data REST - API 테스트")
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 스케줄 리스트 조회")
    @Test
    void givenNothing_whenRequesting_thenReturnsSchedulesJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/schedules"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/Hal+json")));
    }

    @DisplayName("[api] 스케줄 단건 조회")
    @Test
    void givenNothing_whenRequesting_thenReturnsScheduleJsonResponse() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/api/schedules/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/Hal+json")));
    }
}
