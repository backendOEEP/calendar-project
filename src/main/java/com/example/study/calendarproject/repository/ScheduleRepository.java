package com.example.study.calendarproject.repository;

import com.example.study.calendarproject.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
