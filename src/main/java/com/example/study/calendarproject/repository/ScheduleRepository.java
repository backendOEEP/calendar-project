package com.example.study.calendarproject.repository;

import com.example.study.calendarproject.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
