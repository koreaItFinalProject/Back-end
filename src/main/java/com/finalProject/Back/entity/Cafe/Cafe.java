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
    private Long id;
    private Long ownerId;
    private String cafeName;
    private String address;
    private String lat;
    private String lng;
    private String category;
    private String img;
    private int reviewCount;
    private int likeCount;
}
