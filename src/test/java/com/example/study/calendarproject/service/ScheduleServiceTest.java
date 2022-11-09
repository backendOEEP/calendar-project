package com.example.study.calendarproject.service;

import com.example.study.calendarproject.domain.Schedule;
import com.example.study.calendarproject.domain.constant.Category;
import com.example.study.calendarproject.domain.constant.RepeatOption;
import com.example.study.calendarproject.dto.ScheduleDto;
import com.example.study.calendarproject.repository.ScheduleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

/**
 * 스케줄 생성
 * 스케줄 수정
 * 스케줄 삭제
 * *** 이후에 할 것 ***
 * 사용자 추가
 * 초대링크 생성하기
 * User api 만들어졌으면 스케줄 조회 가능하도록 테스트
 */
@ExtendWith(MockitoExtension.class)
class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService sut;  //System Under Test

    @Mock
    private ScheduleRepository scheduleRepository;


    @DisplayName("스케줄 정보를 입력하면, 스케줄을 생성한다.")
    @Test
    void givenScheduleInfo_whenSavingSchedule_thenSaveSchedule() {
        //Given
        ScheduleDto dto = createScheduleDto();
        given(scheduleRepository.save(any(Schedule.class))).willReturn(createSchedule());
        //When
        sut.saveSchedule(dto);

        //Then
        then(scheduleRepository).should().save(any(Schedule.class));
    }

    @DisplayName("스케줄 정보 입력 도중 잘못된 시간 입력시(start_time이 end_time보다 늦을 경우) 예외를 발생시키고 메시지를 남긴다..")
    @Test
    void givenScheduleInfoWithIllegalTime_whenCreateSchedule_thenThrowException() {
        Throwable t = catchThrowable(() -> createScheduleDto(
                3L,
                "private session",
                "경북대학교 도서관",
                Category.ETC,
                LocalDateTime.of(2022, 11, 25, 20, 30),
                LocalDateTime.of(2022, 11, 25, 19, 30),
                "초청강연",
                RepeatOption.DAY
        ));

        //Then
        assertThat(t)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("끝나는 시간이 시작 시간보다 빠를 수는 없습니다.");
    }

    @DisplayName("스케줄의 수정정보를 입력하면, 스케줄을 수정한다.")
    @Test
    void givenModifiedScheduleInfo_whenUpdatingSchedule_thenUpdatesSchedule() {
        //Given
        Schedule schedule = createSchedule();
        ScheduleDto dto = createScheduleDto();
        given(scheduleRepository.getReferenceById(dto.getId())).willReturn(schedule);

        //When
        sut.updateSchedule(dto);

        //Then
        assertThat(schedule)
                .hasFieldOrPropertyWithValue("name", dto.getName())
                .hasFieldOrPropertyWithValue("location", dto.getLocation())
                .hasFieldOrPropertyWithValue("category", dto.getCategory())
                .hasFieldOrPropertyWithValue("start_time", dto.getStart_time())
                .hasFieldOrPropertyWithValue("end_time", dto.getEnd_time())
                .hasFieldOrPropertyWithValue("description", dto.getDescription())
                .hasFieldOrPropertyWithValue("repeatOption", dto.getRepeatOption());
        then(scheduleRepository).should().getReferenceById(dto.getId());
    }

    @DisplayName("없는 스케줄의 수정정보를 입력하면, 경고 로고를 찍고 아무것도 하지 않는다.")
    @Test
    void givenNonexistentScheduleInfo_whenUpdatingSchedule_thenLogWarningAndDoesNothing() {
        //Given
        ScheduleDto dto = createScheduleDto(
                3L,
                "private session",
                "경북대학교 도서관",
                Category.ETC,
                LocalDateTime.of(2022, 11, 25, 20, 30),
                LocalDateTime.of(2022, 11, 25, 19, 30),
                "초청강연",
                RepeatOption.DAY
        );
        given(scheduleRepository.getReferenceById(dto.getId())).willThrow(EntityNotFoundException.class);

        //When
        sut.updateSchedule(dto);

        //Then
        then(scheduleRepository).should().getReferenceById(dto.getId());
    }

    @DisplayName("스케줄의 ID를 입력하면, 스케줄을 삭제한다.")
    @Test
    void givenScheduleId_whenDeletingSchedule_thenDeletesSchedule() {
        //Given
        Long scheduleId = 1L;
        willDoNothing().given(scheduleRepository).deleteById(scheduleId);

        //When
        sut.deleteSchedule(scheduleId);

        //Then
        then(scheduleRepository).should().deleteById(scheduleId);
    }

    private Schedule createSchedule() {
        return Schedule.of("Open Session",
                "경대 북문 블라인드 멜론",
                Category.STUDY,
                LocalDateTime.of(2022, 11, 20, 16, 30),
                LocalDateTime.of(2022, 11, 20, 19, 30),
                "정기 스터디 모임 활동",
                RepeatOption.WEEK);
    }

    private ScheduleDto createScheduleDto() {
        return createScheduleDto(
                3L,
                "private session",
                "경북대학교 도서관",
                Category.ETC,
                LocalDateTime.of(2022, 11, 25, 16, 30),
                LocalDateTime.of(2022, 11, 25, 19, 30),
                "초청강연",
                RepeatOption.DAY);
    }

    private ScheduleDto createScheduleDto(Long id, String name, String location, Category category, LocalDateTime start_time, LocalDateTime end_time, String description, RepeatOption repeatOption) {
        return ScheduleDto.of(
                id,
                name,
                location,
                category,
                start_time,
                end_time,
                description,
                repeatOption
        );
    }

}