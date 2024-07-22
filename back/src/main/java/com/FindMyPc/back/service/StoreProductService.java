package com.FindMyPc.back.service;


import com.FindMyPc.back.entity.StoreProduct;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StoreProductService {
    List<StoreProduct> getAllAffected();

    Optional<StoreProduct> getAffectedById(int id);

    StoreProduct saveAffected(StoreProduct storeProduct);
    
    StoreProduct updateAffected(StoreProduct storeProduct);


    void deleteAffected(int id);

    Page<StoreProduct> getAffectedPaginated(int page, int size);
}
