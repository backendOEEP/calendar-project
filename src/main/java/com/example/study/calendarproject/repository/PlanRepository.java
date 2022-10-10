package com.example.study.calendarproject.repository;

import com.example.study.calendarproject.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
