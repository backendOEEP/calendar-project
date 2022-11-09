package com.example.study.calendarproject.dto;

import com.example.study.calendarproject.domain.Schedule;
import com.example.study.calendarproject.domain.constant.Category;
import com.example.study.calendarproject.domain.constant.RepeatOption;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleDto {
    Long id;
    String name;
    String location;
    Category category;
    LocalDateTime start_time;
    LocalDateTime end_time;
    String description;
    RepeatOption repeatOption;

    protected ScheduleDto() {
    }

    private ScheduleDto(Long id, String name, String location, Category category, LocalDateTime start_time, LocalDateTime end_time, String description, RepeatOption repeatOption) {
        validateDateTime(start_time, end_time);
        this.id = id;
        this.name = name;
        this.location = location;
        this.category = category;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.repeatOption = repeatOption;
    }

    public static ScheduleDto of(Long id, String name, String location, Category category, LocalDateTime start_time, LocalDateTime end_time, String description, RepeatOption repeatOption) {
        return new ScheduleDto(id, name, location, category, start_time, end_time, description, repeatOption);
    }

    public static ScheduleDto from(Schedule entity) {
        return new ScheduleDto(
                entity.getId(),
                entity.getName(),
                entity.getLocation(),
                entity.getCategory(),
                entity.getStart_time(),
                entity.getEnd_time(),
                entity.getDescription(),
                entity.getRepeatOption()
        );
    }

    public Schedule toEntity() {
        return Schedule.of(name, location, category, start_time, end_time, description, repeatOption);
    }

    private void validateDateTime(LocalDateTime start_time, LocalDateTime end_time) {
        if(start_time.isAfter(end_time))
            throw new IllegalArgumentException("끝나는 시간이 시작 시간보다 빠를 수는 없습니다.");
    }
}
