package com.finalProject.Back.entity.Cafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeMenuImg {
    private Long cafeId;
    private int index;
    private String menu;
}
