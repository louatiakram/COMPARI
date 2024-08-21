package com.FindMyPc.back.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsImgsResponseDto {
    private Long id;
    private String imgName;
    private List<ProductsResponseDto> products;


}
