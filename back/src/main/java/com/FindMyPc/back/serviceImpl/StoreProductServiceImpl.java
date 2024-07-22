package com.FindMyPc.back.serviceImpl;


import com.FindMyPc.back.entity.StoreProduct;
import com.FindMyPc.back.repository.StoreProductRepository;
import com.FindMyPc.back.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    private StoreProductRepository storeProductRepository;

    @Override
    public List<StoreProduct> getAllAffected() {
        return storeProductRepository.findAll();
    }

    @Override
    public Optional<StoreProduct> getAffectedById(int id) {
        return storeProductRepository.findById(id);
    }

    @Override
    public StoreProduct saveAffected(StoreProduct storeProduct) {
        return storeProductRepository.save(storeProduct);
    }
    
    @Override
    public StoreProduct updateAffected(StoreProduct storeProduct) {
        return storeProductRepository.save(storeProduct);
    }

    @Override
    public void deleteAffected(int id) {
        storeProductRepository.deleteById(id);
    }

    @Override
    public Page<StoreProduct> getAffectedPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return storeProductRepository.findAll(pageable);
    }
}
