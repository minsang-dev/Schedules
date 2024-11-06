package com.example.schedules.repository;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import com.example.schedules.dto.SchedulesUpdateResponseDto;
import com.example.schedules.entity.Schedules;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SchedulesRepositoryImpl implements SchedulesRepository {

    private final Map<Long, Schedules> schedulesList = new HashMap<>();

    // 일정 생성
    @Override
    public SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto) {
        Long schedulesId = schedulesList.isEmpty() ? 1 : (Collections.max(schedulesList.keySet()) + 1);
        Schedules schedules = new Schedules(schedulesId, schedulesRequestDto);

        schedules.setId(schedulesId);
        schedulesList.put(schedulesId, schedules);

        return new SchedulesResponseDto(schedules); // 메서드의 반환타입과 return은 맞춰주기
    }

    // 일정 다건 조회
    @Override
    public List<SchedulesResponseDto> findAllSchedules() {
        // init List
        List<SchedulesResponseDto> allSchedules = new ArrayList<>();
        for (Schedules schedules : schedulesList.values()) {
            SchedulesResponseDto schedulesResponseDto = new SchedulesResponseDto(schedules);
            allSchedules.add(schedulesResponseDto);
        }
        return allSchedules;
    }

    // 일정 단건 조회
    @Override
    public Schedules findScheduleById(Long id) {
        return schedulesList.get(id);
    }

    // 일정 수정
    @Override
    public SchedulesUpdateResponseDto updateSchedules(Long id, SchedulesUpdateRequestDto schedulesUpdateRequestDto) {
        Schedules schedules = schedulesList.get(id);
        if (schedulesUpdateRequestDto.getPassword().equals(schedules.getPassword())) {
            schedules.update(schedulesUpdateRequestDto);
            schedulesList.put(id, schedules);
        }
        return new SchedulesUpdateResponseDto(schedules);
    }

    // 일정 삭제
    @Override
    public void deleteSchedules(Long id, String password) {
        Schedules schedules = schedulesList.get(id);

        if (password.equals(schedules.getPassword())) {
            schedulesList.remove(id);
        }
    }
}
