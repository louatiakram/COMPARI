package com.FindMyPc.back.repository;


import com.FindMyPc.back.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer> {
    StoreProduct findByStoreIdAndProductIdAndPriceBD(int storeId, Long product_id, double priceBD);

}
