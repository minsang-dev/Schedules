package com.example.schedules.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulesUpdateResponseDto {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final LocalDateTime update_date;

    public SchedulesUpdateResponseDto(Long id, String name, String title, String content, LocalDateTime update_date) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
        this.update_date = update_date;
    }
}