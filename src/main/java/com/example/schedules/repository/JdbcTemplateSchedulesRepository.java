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

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateSchedulesRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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
        parameters.put("createDate", nowDate);
        parameters.put("updateDate", nowDate);

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

    @Override
    public List<SchedulesResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedules order by updateDate desc", schedulesRowMapper());
    }

    @Override
    public Optional<Schedules> findScheduleById(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id=?", schedulesRowMapper2(), id);
        return result.stream().findAny();
    }
    @Override
    public Schedules findSchedulesByIdOrElseThrow(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id = ?", schedulesRowMapper2(), id);
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists id = " + id));
    }

    @Override
    public int updateSchedules(Long id, String title, String content) {
        return jdbcTemplate.update("update schedules set title = ?, content = ?, updateDate = ? where id = ?", title, content, LocalDateTime.now(), id);
    }

    @Override
    public int deleteSchedules(Long id, String password) {
        return jdbcTemplate.update("delete from schedules where id = ? and password = ?", id, password);
    }

    private RowMapper<SchedulesResponseDto> schedulesRowMapper() {
        return new RowMapper<SchedulesResponseDto>() {
            @Override
            public SchedulesResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new SchedulesResponseDto(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createDate").toLocalDateTime(),
                        rs.getTimestamp("updateDate").toLocalDateTime()
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
                            rs.getTimestamp("createDate").toLocalDateTime(),
                            rs.getTimestamp("updateDate").toLocalDateTime()
                    );
                }
            };
        }
    }