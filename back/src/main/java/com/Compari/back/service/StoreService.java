package com.Compari.back.service;

import com.Compari.back.ResponseDto.StoreResponseDto;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<StoreResponseDto> getAllStores();

    Optional<StoreResponseDto> getStoreById(int id);

    StoreResponseDto saveStore(StoreResponseDto StoreResponseDto);

    StoreResponseDto updateStore(StoreResponseDto StoreResponseDto);


    void deleteStore(int id);
}
