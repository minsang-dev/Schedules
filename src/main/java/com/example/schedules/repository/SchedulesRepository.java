package com.example.schedules.repository;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import com.example.schedules.dto.SchedulesUpdateResponseDto;
import com.example.schedules.entity.Schedules;

import java.util.List;

public interface SchedulesRepository {
    // 일정 생성
    SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto);

    SchedulesResponseDto saveSchedules(Schedules schedules);

    List<SchedulesResponseDto> findAllSchedules();

    SchedulesResponseDto findScheduleById(Long id);

    SchedulesUpdateResponseDto updateScheduleById(Long id, SchedulesUpdateRequestDto schedulesUpdateRequestDto);

    void deleteSchedules(Long id, String password);
    // void deleteAllSchedules();
}
