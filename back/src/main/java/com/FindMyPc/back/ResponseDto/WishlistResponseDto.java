package com.FindMyPc.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponseDto {
    private int wishlistID;
    private UserResponseDto user;        

    
}
