package com.example.schedules.service;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import com.example.schedules.dto.SchedulesUpdateResponseDto;
import com.example.schedules.entity.Schedules;
import com.example.schedules.repository.SchedulesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        return savedSchedules;
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
        Optional<Schedules> optionalSchedules = schedulesRepository.findScheduleById(id);

        if (optionalSchedules.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new SchedulesResponseDto(optionalSchedules.get());
    }

    // 일정 수정
    @Transactional
    @Override
    public SchedulesUpdateResponseDto updateSchedules(Long id, SchedulesUpdateRequestDto schedulesUpdateRequestDto) {
        // schedulesRepository.findScheduleById(id);

        if (schedulesUpdateRequestDto.getTitle() == null || schedulesUpdateRequestDto.getContent() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and content are required values.");
        }

        Optional<Schedules> schedules = schedulesRepository.findScheduleById(id);
        if(!schedules.get().getPassword().equals(schedulesUpdateRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match.");
        }

        int updateRow = schedulesRepository.updateSchedules(id, schedulesUpdateRequestDto.getTitle(), schedulesUpdateRequestDto.getContent());

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Optional<Schedules> optionalSchedules = schedulesRepository.findScheduleById(id);

        return new SchedulesUpdateResponseDto(optionalSchedules.get());

    }
 // 일정 삭제
    @Override
    public void deleteSchedules(Long id,String password) {

        int deleteRow = schedulesRepository.deleteSchedules(id, password);

        if (deleteRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }
}