package com.example.schedules.dto;

import lombok.Getter;

@Getter
public class SchedulesUpdateRequestDto {
        private String username;
        private String password;
        private String title;
        private String content;
}