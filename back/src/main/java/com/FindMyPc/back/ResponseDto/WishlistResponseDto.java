package com.FindMyPc.back.ResponseDto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponseDto {
    private int wishlistID;
    private UserResponseDto user;        
    private List<ProductResponseDto> products;
    
}
