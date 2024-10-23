package com.finalProject.Back.dto.request;

import com.finalProject.Back.entity.Cafe.Cafe;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqCafeDto {
    private int ownerId;
    @NotBlank(message = "카페 이름을 입력해주세요.")
    private String cafename;
    @NotBlank(message = "카페 주소를 입력해주세요.")
    private String address;
    @NotBlank
    private String lat;
    @NotBlank
    private String lng;

    public Cafe toEntity() {
        return Cafe.builder()
                .owner_id(ownerId)
                .cafeName(cafename)
                .address(address)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
