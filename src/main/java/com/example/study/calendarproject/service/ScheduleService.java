package com.example.study.calendarproject.service;

import com.example.study.calendarproject.domain.Schedule;
import com.example.study.calendarproject.dto.ScheduleDto;
import com.example.study.calendarproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional(readOnly = true)
    public Schedule getSchedule(Long scheduleId) {
        return null;
    }

    public Schedule saveSchedule(ScheduleDto dto) {
        return null;
    }

    public void updateSchedule(ScheduleDto Schedule) {
    }

    public void deleteSchedule(Long scheduleId) {
    }

}
