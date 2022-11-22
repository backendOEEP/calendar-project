package com.example.study.calendarproject.repository;

import com.example.study.calendarproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(Long userId);
}
