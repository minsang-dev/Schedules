package com.example.schedules.dto;
// 전달하는 클래스
import lombok.Getter;

@Getter
public class SchedulesRequestDto {
    private String username;
    private String password;
    private String title;
    private String content;
}