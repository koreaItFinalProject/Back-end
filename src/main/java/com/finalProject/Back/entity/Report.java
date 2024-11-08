package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private Long id;
    private Long contentId;
    private String content;
    private LocalDate reportDate;
    private Long reportId;
    private String reportType;
}
