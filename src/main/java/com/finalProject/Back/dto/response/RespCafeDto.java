package com.finalProject.Back.dto.response;

import lombok.Builder;
import lombok.Data;


public class RespCafeDto {

    @Data
    @Builder
    public static class RespCafeLikeDto {
        private Long cafeLikeId;
        private int likeCount;
    }
}
