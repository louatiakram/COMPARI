package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.ResponseDto.StoreProductResponseDto;
import com.FindMyPc.back.entity.StoreProduct;
import com.FindMyPc.back.repository.StoreProductRepository;
import com.FindMyPc.back.service.StoreProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StoreProductResponseDto> getAllAffected() {
        List<StoreProduct> storeProducts = storeProductRepository.findAll();
        return storeProducts.stream()
                .map(storeProduct -> modelMapper.map(storeProduct, StoreProductResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StoreProductResponseDto> getAffectedById(int id) {
        return storeProductRepository.findById(id)
                .map(storeProduct -> modelMapper.map(storeProduct, StoreProductResponseDto.class));
    }

    @Override
    public StoreProductResponseDto saveAffected(StoreProductResponseDto storeProductResponseDto) {
        StoreProduct storeProduct = modelMapper.map(storeProductResponseDto, StoreProduct.class);
        StoreProduct savedStoreProduct = storeProductRepository.save(storeProduct);
        return modelMapper.map(savedStoreProduct, StoreProductResponseDto.class);
    }

    @Override
    public StoreProductResponseDto updateAffected(StoreProductResponseDto storeProductResponseDto) {
        if (storeProductRepository.existsById(storeProductResponseDto.getId())) {
            StoreProduct storeProduct = modelMapper.map(storeProductResponseDto, StoreProduct.class);
            StoreProduct updatedStoreProduct = storeProductRepository.save(storeProduct);
            return modelMapper.map(updatedStoreProduct, StoreProductResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteAffected(int id) {
        if (storeProductRepository.existsById(id)) {
            storeProductRepository.deleteById(id);
        }
        // Handle the case where the store product does not exist
    }

    @Override
    public Page<StoreProductResponseDto> getAffectedPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StoreProduct> storeProductPage = storeProductRepository.findAll(pageable);
        return storeProductPage.map(storeProduct -> modelMapper.map(storeProduct, StoreProductResponseDto.class));
    }

}
