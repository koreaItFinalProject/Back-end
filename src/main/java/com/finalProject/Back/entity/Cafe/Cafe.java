package com.finalProject.Back.entity.Cafe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cafe {
    private int id;
    private int owner_id;
    private String cafeName;
    private String address;
    private String lat;
    private String lng;
    private String category;
    private int reviewCount;
    private int likeCount;
}
