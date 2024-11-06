package com.example.schedules.service;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import com.example.schedules.dto.SchedulesUpdateResponseDto;

import java.util.List;

public interface SchedulesService {
    SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto);

    List<SchedulesResponseDto> findAllSchedules();

    SchedulesResponseDto findScheduleById(Long id);

    SchedulesUpdateResponseDto updateSchedules(Long id, SchedulesUpdateRequestDto schedulesUpdateRequestDto);

    void deleteSchedules(long id);

}
