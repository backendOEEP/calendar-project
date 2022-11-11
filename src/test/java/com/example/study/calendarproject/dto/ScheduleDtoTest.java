package com.example.study.calendarproject.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleDtoTest {

    @Test
    void validateDateTime() {
        LocalDateTime start_time = LocalDateTime.of(2022, 11, 25, 16, 30);
        LocalDateTime end_time = LocalDateTime.of(2022, 11, 24, 16, 30);

        Throwable t = catchThrowable(() ->validateDateTime(start_time, end_time));
        assertThat(t)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("끝나는 시간이 시작 시간보다 빠를 수는 없습니다.");
    }


    void validateDateTime(LocalDateTime start_time, LocalDateTime end_time) {
        if (start_time.isAfter(end_time))
            throw new IllegalArgumentException("끝나는 시간이 시작 시간보다 빠를 수는 없습니다.");
    }
}