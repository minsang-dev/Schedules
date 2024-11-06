package com.example.schedules.dto;
// 응답하는 클래스
import com.example.schedules.entity.Schedules;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
//import org.springframework.scheduling.annotation.Schedules;

@Getter
@AllArgsConstructor
public class SchedulesResponseDto {
    private final Long id;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime updateDate;
    private final LocalDateTime createDate;

    public SchedulesResponseDto(Schedules schedules) {
        this.id = schedules.getId();
        this.username = schedules.getUsername();
        this.title = schedules.getTitle();
        this.content = schedules.getContent();
        this.updateDate = schedules.getUpdateDate();
        this.createDate = schedules.getCreateDate();
    }
}