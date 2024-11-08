package com.example.schedules.repository;

import com.example.schedules.dto.SchedulesRequestDto;
import com.example.schedules.dto.SchedulesResponseDto;
import com.example.schedules.entity.Schedules;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateSchedulesRepository implements SchedulesRepository{
    //Jdbc 템플릿 적용
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchedulesRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    // 일정 생성
    @Override
    public SchedulesResponseDto saveSchedules(SchedulesRequestDto schedulesRequestDto) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        LocalDateTime nowDate = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedulesRequestDto.getTitle());
        parameters.put("content", schedulesRequestDto.getContent());
        parameters.put("username", schedulesRequestDto.getUsername());
        parameters.put("password", schedulesRequestDto.getPassword());
        parameters.put("create_date", nowDate);
        parameters.put("update_date", nowDate);

        // 생성된 데이터의 고유 id를 반환 (Insert문으로 치환)
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new SchedulesResponseDto(
                key.longValue(),
                schedulesRequestDto.getUsername(),
                schedulesRequestDto.getTitle(),
                schedulesRequestDto.getContent(),
                nowDate,
                nowDate
        );
    }
    
    // 일정 전체 조회
    @Override
    public List<SchedulesResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedules order by update_date desc", schedulesRowMapper());
    }
    
    // 일정 단건 조회
    @Override
    public Optional<Schedules> findScheduleById(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id = ?", schedulesRowMapper2(), id);
        return result.stream().findAny(); // 어차피 result 개수는 0이거나 1이니까 findAny로 아무거나 가져와도 됨.
    }
    
    @Override
    public Schedules findSchedulesByIdOrElseThrow(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id = ?", schedulesRowMapper2(), id);
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists id = " + id));
    }
    
    // 일정 수정
    @Override
    public int updateSchedules(Long id, String title, String content) {
        return jdbcTemplate.update("update schedules set title = ?, content = ?, update_date = ? where id = ?", title, content, LocalDateTime.now(), id);
    }
    
    // 일정 삭제
    @Override
    public int deleteSchedules(Long id, String password) {
        return jdbcTemplate.update("delete from schedules where id = ? and password = ?", id, password);
    }

    private RowMapper<SchedulesResponseDto> schedulesRowMapper() {
        return new RowMapper<SchedulesResponseDto>() {

            // Resultset : query문 실행 결과값, 자바 값으로 사용 위해 rs.get-사용
            @Override
            public SchedulesResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulesResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("create_date").toLocalDateTime(),
                        rs.getTimestamp("update_date").toLocalDateTime()
                );
            }
        };
    }

        private RowMapper<Schedules> schedulesRowMapper2() {
            return new RowMapper<Schedules>() {
                @Override
                public Schedules mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Schedules(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("title"),
                            rs.getString("content"),
                            rs.getTimestamp("create_date").toLocalDateTime(),
                            rs.getTimestamp("update_date").toLocalDateTime()
                    );
                }
            };
        }
    }