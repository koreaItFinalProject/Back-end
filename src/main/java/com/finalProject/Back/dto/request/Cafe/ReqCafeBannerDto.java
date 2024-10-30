package com.finalProject.Back.dto.request.Cafe;

import com.finalProject.Back.entity.Cafe.Cafe;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqCafeBannerDto {
    private Long cafeId;
    private String img;

    public Cafe toEntity() {
        return Cafe.builder()
                .id(cafeId)
                .img(img)
                .build();
    }
}
