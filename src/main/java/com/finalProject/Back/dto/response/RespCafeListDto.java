package com.finalProject.Back.dto.response;

import com.finalProject.Back.entity.Cafe;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespCafeListDto {
    private List<Cafe> cafeList;
}
