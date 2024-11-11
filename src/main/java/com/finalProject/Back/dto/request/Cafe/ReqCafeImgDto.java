package com.finalProject.Back.dto.request.Cafe;

import com.finalProject.Back.entity.Cafe.CafeMenuImg;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqCafeImgDto {
    String imageUrl;
    int index;
    Long cafeId;

    public CafeMenuImg toEntity() {
        return CafeMenuImg.builder()
                .menu(imageUrl)
                .index(index)
                .cafeId(cafeId)
                .build();
    }
}