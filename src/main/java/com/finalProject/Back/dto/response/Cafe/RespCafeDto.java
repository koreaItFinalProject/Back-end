package com.finalProject.Back.dto.response.Cafe;

import lombok.Builder;
import lombok.Data;


public class RespCafeDto {

    @Data
    @Builder
    public static class RespCafeLikeDto {
        private Long cafeLikeId;
        private int likeCount;
    }

    @Data
    @Builder
    public static class RespCafeInfoDto {
        private Long cafeId;
        private String cafeName;
    }
}
