package com.finalProject.Back.entity;

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
    private int ownerId;
    private String cafeName;
    private String address;
    private int likeCount;
    private int viewCount;
    private String lat;
    private String lng;
    private String category;
}
