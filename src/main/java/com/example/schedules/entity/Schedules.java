package com.example.schedules.entity;
// 저장하는 클래스
import com.example.schedules.dto.SchedulesRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedules { //서버에서 저장하는 데이터
    private final Long id;
    private String user_name; // 작성자 명
    private String title; // 할 일 제목
    private String contents; // 할 일
    private final LocalDateTime create_date; // 생성일
    private final LocalDateTime update_date; // 수정일

    public Schedules (Long id, String user_name, String title, String contents) {
        this.id = id;
        this.user_name = user_name;
        this.title = title;
        this.contents = contents;
        this.create_date = LocalDateTime.now();
        this.update_date = LocalDateTime.now();
    }

    public void update(SchedulesRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContent();
    }

    public void update(String user_name, String password, String title, String content) {
        this.user_name = user_name;
        this.title = title;
        this.contents = content;
    }
}
