package com.FindMyPc.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductResponseDto {
    private int id;
    private float priceBD;
    private float priceAD;
    private ProductResponseDto product;  
    private StoreResponseDto store;      
}