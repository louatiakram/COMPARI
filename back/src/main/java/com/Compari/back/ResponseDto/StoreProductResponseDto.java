package com.Compari.back.ResponseDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreProductResponseDto {
    private int id;

    private float priceAD; // Price from the product
    private double priceBD; // Price from the scraping source
    private int storeId; // Store ID (constant 1 for now)
    private StoreResponseDto store;
}