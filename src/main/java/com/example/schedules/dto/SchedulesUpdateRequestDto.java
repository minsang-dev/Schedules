package com.example.schedules.dto;

import lombok.Getter;

@Getter
public class SchedulesUpdateRequestDto { //수정 요청정보
        private String username;
        private String password;
        private String title;
        private String content;
}