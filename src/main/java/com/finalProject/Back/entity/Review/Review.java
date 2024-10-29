package com.finalProject.Back.entity.Review;

import com.finalProject.Back.entity.User;
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
    private Double rating;
    private String review;
    private LocalDate writeDate;

    private User user;
}
