package com.example.schedules.entity;
// 저장하는 클래스

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class Schedules {
    public static Schedules schedules; //서버에서 저장하는 데이터

    private Long id;
    private String username; // 작성자 명
    private String password;
    private String title; // 할 일 제목
    private String content; // 할 일
    private LocalDateTime createDate; // 생성일
    private LocalDateTime updateDate; // 수정일

    public Schedules (Long id, String username,String password, String title, String content) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public Schedules(Long id, SchedulesRequestDto schedulesRequestDto) {
        this.id = id;
        this.username = schedulesRequestDto.getUsername();
        this.password = schedulesRequestDto.getPassword();
        this.title = schedulesRequestDto.getTitle();
        this.content = schedulesRequestDto.getContent();
    }

    public Schedules(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(SchedulesUpdateRequestDto updateRequestDto) {
        this.username = updateRequestDto.getUsername();
        this.title = updateRequestDto.getTitle();
        this.content = updateRequestDto.getContent();
    }

    public void update(String username, String password, String title, String content) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
