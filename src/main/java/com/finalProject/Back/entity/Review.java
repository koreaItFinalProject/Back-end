package com.finalProject.Back.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private Long id;
    private Long cafeId;
    private Long writerId;
    private String nickname;
    private int rating;
    private String category;
    private String review;
    private LocalDate writeDate;
}
