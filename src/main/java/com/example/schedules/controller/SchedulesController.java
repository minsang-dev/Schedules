package com.example.schedules.controller;

import com.example.schedules.dto.*;
import com.example.schedules.service.SchedulesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/schedules") // prefix
public class SchedulesController {

    /*
    생성자   주입 @param schedulesService
     */
    private final SchedulesService schedulesService;

    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    // 일정 생성 http://localhost:8080/schedules
    @PostMapping
    public ResponseEntity<SchedulesResponseDto> createSchedule(@RequestBody SchedulesRequestDto schedulesRequestDto) {
        // Service Layer 호출, 응답
        return new ResponseEntity<>(schedulesService.saveSchedules(schedulesRequestDto), HttpStatus.CREATED);
    }
    
    // 전체 일정 조회 http://localhost:8080/schedules
    @GetMapping
    public List<SchedulesResponseDto> findAllSchedules() {
        return schedulesService.findAllSchedules();
    }

    // 선택 일정 조회 http://localhost:8080/schedules/6
    @GetMapping("/{id}")
    public ResponseEntity<SchedulesResponseDto> findSchedulesById(@PathVariable Long id) {
        return new ResponseEntity<>(schedulesService.findScheduleById(id), HttpStatus.OK);
    }

    // 일정 수정 http://localhost:8080/schedules/6
    @PutMapping("/{id}")
    public ResponseEntity<SchedulesUpdateResponseDto> updateSchedules(
            @PathVariable Long id,
            @RequestBody SchedulesUpdateRequestDto schedulesUpdateRequestDto
    ) {
        return new ResponseEntity<>(schedulesService.updateSchedules(id, schedulesUpdateRequestDto), HttpStatus.OK);
    }
    
    // 일정 삭제 http://localhost:8080/schedules/8
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedules(@PathVariable Long id, @RequestBody SchedulesDeleteRequestDto dto) {
        schedulesService.deleteSchedules(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);

        }
    }