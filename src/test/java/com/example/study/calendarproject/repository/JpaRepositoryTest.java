package com.example.study.calendarproject.repository;

import com.example.study.calendarproject.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@DataJpaTest
public class JpaRepositoryTest {
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final ScheduleRepository scheduleRepository;

    public JpaRepositoryTest(
        @Autowired UserRepository userRepository,
        @Autowired PlanRepository planRepository,
        @Autowired ScheduleRepository scheduleRepository
    ){
        this.userRepository = userRepository;
        this.planRepository = planRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @DisplayName("user select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){

        List<User> users = userRepository.findAll();

        assertThat(users).isNotNull().hasSize(4);
    }

    @DisplayName("user insert 테스트")
    @Test
    void givenTestDate_whenInserting_thenWorksFine() {

        long previousCount = userRepository.count();

        User savedUser = userRepository.save(User.of("e", "asd", "Gildong", "e@mmail.com"));

        assertThat(userRepository.count()).isEqualTo(previousCount+1);
    }

    @DisplayName("user update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

        User user = userRepository.findById("a").orElseThrow();
        user.setNickname("Yun");

        User savedUser = userRepository.saveAndFlush(user);

        assertThat(savedUser).hasFieldOrPropertyWithValue("nickname", "Yun");
    }

    @DisplayName("user delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        User user = userRepository.findById("a").orElseThrow();
        long previousUserCount = userRepository.count();
        long previousPlanCount = planRepository.count();
        int deletedPlanSize = user.getUserPlans().size();

        userRepository.delete(user);

        assertThat(userRepository.count()).isEqualTo(previousUserCount - 1);
        assertThat(planRepository.count()).isEqualTo(previousPlanCount - deletedPlanSize);
    }
}
