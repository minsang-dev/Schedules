package com.example.schedules.dto;
// 응답하는 클래스
import com.example.schedules.entity.Schedules;
import lombok.Getter;

import java.time.LocalDateTime;
//import org.springframework.scheduling.annotation.Schedules;

@Getter
public class SchedulesResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    
    public SchedulesResponseDto(Schedules schedules) {
        this.id = schedules.getId();
        this.username = schedules.getUser_name();
        this.title = schedules.getTitle();
        this.contents = schedules.getContents();
        this.create_date = schedules.getCreate_date();
        this.update_date = schedules.getUpdate_date();
    }
}