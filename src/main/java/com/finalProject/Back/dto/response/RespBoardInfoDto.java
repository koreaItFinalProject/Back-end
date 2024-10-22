package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RespBoardInfoDto {
    private Long id;
    private String title;
    private String view_count;
    private String write_date;
}
