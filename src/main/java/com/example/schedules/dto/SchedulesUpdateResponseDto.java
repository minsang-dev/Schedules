package com.example.schedules.dto;

import com.example.schedules.entity.Schedules;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulesUpdateResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime updateDate;

    public SchedulesUpdateResponseDto(Long id, String username, String title, String content, LocalDateTime updateDate) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
        this.updateDate = updateDate;
    }

    public SchedulesUpdateResponseDto(Schedules schedules) {
        this.id = schedules.getId();
        this.username = schedules.getUsername();
        this.title = schedules.getTitle();
        this.content = schedules.getContent();
        this.updateDate = schedules.getUpdateDate();
    }
}