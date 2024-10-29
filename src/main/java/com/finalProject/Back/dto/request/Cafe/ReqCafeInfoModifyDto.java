package com.finalProject.Back.dto.request.Cafe;

import com.finalProject.Back.entity.Cafe.Cafe;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqCafeInfoModifyDto {
    private Long cafeId;
    private String cafeName;
    private String address;
    private String category;

    public Cafe toEntity() {
        return Cafe.builder()
                .id(cafeId)
                .cafeName(cafeName)
                .address(address)
                .category(category)
                .build();
    }
}
