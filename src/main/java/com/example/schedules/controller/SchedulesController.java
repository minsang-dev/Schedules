package com.example.schedules.controller;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.entity.Schedules;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/schedules") // prefix
public class SchedulesController {
    private final Map<Long, Schedules> schedulesList = new HashMap<>();

    // 일정 생성
    @PostMapping("/schedules")
    public SchedulesResponseDto createSchedules(@RequestBody SchedulesRequestDto requestDto) {
        Long scheduleId = schedulesList.isEmpty() ? 1 : Collections.max(schedulesList.keySet()) + 1;
        // 요청받은 데이터로 schedules 객체 생성
        Schedules schedules = new Schedules(scheduleId, requestDto.getUser_name(), requestDto.getTitle(), requestDto.getContent());
        // DB에 schedules 저장
        schedulesList.put(scheduleId, schedules);
        return new SchedulesResponseDto(schedules);
    }
    
    // 일정 조회
    @GetMapping("/schedules")
    public SchedulesResponseDto findSchedulesById(@PathVariable Long id) {
        Schedules schedules = schedulesList.get(id);
        return new SchedulesResponseDto(schedules);
    }
    
    // 일정 수정
    @PutMapping("/schedules/{id}")
    public SchedulesResponseDto updateSchedulesById(@PathVariable Long id, @RequestBody SchedulesRequestDto SchedulesRequestDto) {
        Schedules schedules = schedulesList.get(id);
        schedules.update(SchedulesRequestDto.getUser_name(), SchedulesRequestDto.getPassword(), SchedulesRequestDto.getTitle(), SchedulesRequestDto.getContent());
        return new SchedulesResponseDto(schedules);
    }
    
    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public void deleteSchedules(@PathVariable Long id) {
        schedulesList.remove(id);
    }
}