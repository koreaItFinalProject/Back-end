package com.finalProject.Back.dto.request.Cafe;

import lombok.Data;

@Data
public class ReqGetCafeDto {
    private String category;
    private String search = "";
}
