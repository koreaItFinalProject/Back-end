package com.finalProject.Back.repository;

import com.finalProject.Back.dto.response.RespReportDto;
import com.finalProject.Back.entity.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    int save(Report report);
    boolean findByReport(Long contentId, String reportType, Long reportId);
    List<RespReportDto> getReport();
}
