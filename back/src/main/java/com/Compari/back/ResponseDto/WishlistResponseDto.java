package com.Compari.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponseDto {
    private int wishlistID;
    private UserResponseDto user;
    private List<ProductsResponseDto> products;

}
