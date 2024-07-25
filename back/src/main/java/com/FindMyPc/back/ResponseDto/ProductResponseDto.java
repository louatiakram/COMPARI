package com.FindMyPc.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private int productID;
    private String name;
    private String description;
    private String image;
    private List<StoreProductResponseDto> storeProduct;
    private List<RatingResponseDto> ratings;
    /* private List<WishlistResponseDto> wishlists;*/

}
 