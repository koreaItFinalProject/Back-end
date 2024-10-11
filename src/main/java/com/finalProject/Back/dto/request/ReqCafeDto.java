package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Cafe;
import lombok.Data;

@Data
public class ReqCafeDto {
    private int ownerId;
    private String cafename;
    private String address;
    private String lat;
    private String lng;

    public Cafe toEntity() {
        return Cafe.builder()
                .ownerId(ownerId)
                .cafeName(cafename)
                .address(address)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
