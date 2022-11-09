package com.example.study.calendarproject.service;

import com.example.study.calendarproject.domain.Schedule;
import com.example.study.calendarproject.dto.ScheduleDto;
import com.example.study.calendarproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(ScheduleDto dto) {
        return scheduleRepository.save(dto.toEntity());
    }

    public void updateSchedule(Long scheduleId, ScheduleDto dto) {
        try {
            Schedule schedule = scheduleRepository.getReferenceById(scheduleId);

            if (dto.getName() != null) {
                schedule.setName(dto.getName());
            }
            if (dto.getLocation() != null) {
                schedule.setLocation(dto.getLocation());
            }
            if (dto.getCategory() != null) {
                schedule.setCategory(dto.getCategory());
            }
            if (dto.getStart_time() != null) {
                schedule.setStart_time(dto.getStart_time());
            }
            if (dto.getEnd_time() != null) {
                schedule.setEnd_time(dto.getEnd_time());
            }
            if (dto.getDescription() != null) {
                schedule.setDescription(dto.getDescription());
            }
            if (dto.getRepeatOption() != null)
                schedule.setRepeatOption(dto.getRepeatOption());
        } catch (EntityNotFoundException e) {
            log.warn("스케줄 업데이트 실패. 스케줄을 수정하는데 필요한 정보를 찾을 수 없습니다.");
        }

    }

    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

}
