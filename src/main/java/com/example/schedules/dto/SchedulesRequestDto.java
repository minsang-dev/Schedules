package com.example.schedules.dto;
// 전달하는 클래스
import lombok.Getter;

@Getter
public class SchedulesRequestDto { //요청 정보
    private String username;
    private String password;
    private String title;
    private String content;
}