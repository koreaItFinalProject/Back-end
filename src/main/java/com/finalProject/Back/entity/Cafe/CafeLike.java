package com.finalProject.Back.entity.Cafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeLike {
    private Long cafeLikeId;
    private Long cafeId;
    private Long userId;
}
