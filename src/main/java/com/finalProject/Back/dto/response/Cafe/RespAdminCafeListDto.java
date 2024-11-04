package com.finalProject.Back.dto.response.Cafe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespAdminCafeListDto {
    private Long id;
    private Long owner_id;
    private String cafeName;
    private String address;
    private String lat;
    private String lng;
    private String category;
    private String img;
}
