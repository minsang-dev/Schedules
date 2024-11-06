package com.example.schedules.repository;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.entity.Schedules;

import java.util.List;
import java.util.Optional;

public interface SchedulesRepository {
    // 일정 생성
    SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto);

    List<SchedulesResponseDto> findAllSchedules();

    Optional<Schedules> findScheduleById(Long id);

    Schedules findSchedulesByIdOrElseThrow(Long id);

    int updateSchedules(Long id, String title, String content);

    int deleteSchedules(Long id, String password);

    // void deleteAllSchedules();
}
