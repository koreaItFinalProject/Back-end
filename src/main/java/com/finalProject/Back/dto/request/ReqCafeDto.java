package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Cafe;
import lombok.Data;

@Data
public class ReqCafeDto {
    private String cafename;
    private String address;
    private String lat;
    private String lng;
    private String category;

    public Cafe toEntity() {
        return Cafe.builder()
                .cafeName(cafename)
                .address(address)
                .lat(lat)
                .lng(lng)
                .category(category)
                .build();
    }
}
