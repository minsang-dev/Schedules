package com.example.schedules.service;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import com.example.schedules.dto.SchedulesUpdateResponseDto;
import com.example.schedules.entity.Schedules;
import com.example.schedules.repository.SchedulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SchedulesServiceImpl implements SchedulesService {

    private final SchedulesRepository schedulesRepository;

    public SchedulesServiceImpl(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    @Override
    public SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto) {
        // DB 저장
        SchedulesResponseDto savedSchedules = schedulesRepository.saveSchedules(schedulesRequestDto);
        return new SchedulesResponseDto(savedSchedules);

    }

    // 전체 조회
    @Override
    public List<SchedulesResponseDto> findAllSchedules() {
//        List<SchedulesResponseDto> allSchedules = schedulesRepository.findAllSchedules();
        return schedulesRepository.findAllSchedules();
    }

    // 단건 조회
    @Override
    public SchedulesResponseDto findScheduleById(Long id) {
        Schedules schedules =schedulesRepository.findScheduleById(id);

        if (schedules == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new SchedulesResponseDto(schedules);
    }

    // 일정 수정
    @Override
    public SchedulesUpdateResponseDto updateSchedules(Long id, SchedulesUpdateRequestDto schedulesUpdateRequestDto) {
        // schedulesRepository.findScheduleById(id);

        Schedules schedules = schedulesRepository.findScheduleById(id);

        if (schedules == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if (schedulesUpdateRequestDto.getTitle() == null || schedulesUpdateRequestDto.getContent() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and content are required values.");
        }
        schedules.update(schedulesUpdateRequestDto);
        return new SchedulesUpdateResponseDto(schedules);

    }

    @Override
    public void deleteSchedules(long id) {
        Schedules schedules = schedulesRepository.findScheduleById(id);

        if (schedules == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        schedulesRepository.deleteSchedules(id, schedules.getPassword());
    }
}