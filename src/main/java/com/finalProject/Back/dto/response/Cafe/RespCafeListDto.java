package com.finalProject.Back.dto.response.Cafe;

import com.finalProject.Back.entity.Cafe.Cafe;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RespCafeListDto {
    private List<Cafe> cafeList;
}
