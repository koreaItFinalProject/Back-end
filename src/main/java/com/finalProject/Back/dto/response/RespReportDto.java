package com.finalProject.Back.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RespReportDto {
    private Long id;
    private Long contentId;
    private String content;
    private String reportType;
    private LocalDate reportDate;
    private Long reportId;
    private Long reportCount;
}
