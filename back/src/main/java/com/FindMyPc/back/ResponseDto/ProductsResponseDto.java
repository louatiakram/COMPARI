package com.FindMyPc.back.ResponseDto;


import com.FindMyPc.back.entity.ProductsImgs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsResponseDto {
    private Long id;
    private String name;
    private String processor;
    private String processorRef;
    private String memory;
    private String hardDrive;
    private String gpu;
    private String gpuRef;
    private String screenSize;
    private String screenType;
    private String touchScreen;
    private String network;
    private String camera;
    private String warranty;
    private String refreshRate;
    private String color;
    private Double price;
    private List<StoreProductResponseDto> storeProduct;
    private List<ProductsImgsResponseDto> productsImgs;
    private List<RatingResponseDto> ratings;

}
 