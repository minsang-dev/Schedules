package com.example.schedules.dto;
// 응답하는 클래스
import com.example.schedules.entity.Schedules;
import lombok.Getter;

import java.time.LocalDateTime;
//import org.springframework.scheduling.annotation.Schedules;

@Getter
public class SchedulesResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
    
    public SchedulesResponseDto(Schedules schedules) {
        this.id = schedules.getId();
        this.username = schedules.getUsername();
        this.title = schedules.getTitle();
        this.content = schedules.getContent();
        this.createDate = schedules.getCreateDate();
        this.updateDate = schedules.getUpdateDate();
    }

    public SchedulesResponseDto(SchedulesResponseDto savedSchedules) {
        this.id = savedSchedules.getId();
        this.username = savedSchedules.getUsername();
        this.title = savedSchedules.getTitle();
        this.content = savedSchedules.getContent();
        this.createDate = savedSchedules.getCreateDate();
        this.updateDate = savedSchedules.getUpdateDate();
    }
}