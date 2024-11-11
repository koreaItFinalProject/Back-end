package com.finalProject.Back.service;

import com.finalProject.Back.dto.request.ReqReportDto;
import com.finalProject.Back.dto.response.RespReportDto;
import com.finalProject.Back.repository.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public String save(ReqReportDto dto){
        if(reportMapper.findByReport(dto.getContentId(), dto.getReportType(), dto.getReportId())){
            return "이미 신고한 " + dto.getReportType() + "입니다.";
        }
        reportMapper.save(dto.toEntity());
        return "신고가 완료되었습니다.";
    }

    public List<RespReportDto> getReport(){
        return reportMapper.getReport();
    }

    public boolean  deleteReport(String type, String content){
        return reportMapper.deleteReport(type, content);
    }
}
