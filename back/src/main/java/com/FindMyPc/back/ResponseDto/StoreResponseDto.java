package com.FindMyPc.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDto {
    private int storeID;
    private String name;
    private String location;
    private String websiteURL;
    private String logo;
    private List<RatingResponseDto> ratings;
}
