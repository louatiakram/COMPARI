package com.FindMyPc.back.service;


import com.FindMyPc.back.ResponseDto.StoreProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StoreProductService {
    List<StoreProductResponseDto> getAllAffected();

    Optional<StoreProductResponseDto> getAffectedById(int id);

    StoreProductResponseDto saveAffected(StoreProductResponseDto StoreProductResponseDto);
    
    StoreProductResponseDto updateAffected(StoreProductResponseDto StoreProductResponseDto);


    void deleteAffected(int id);

    Page<StoreProductResponseDto> getAffectedPaginated(int page, int size);
}
