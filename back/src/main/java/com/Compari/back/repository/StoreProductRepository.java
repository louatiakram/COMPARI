package com.Compari.back.repository;


import com.Compari.back.entity.StoreProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreProductRepository extends JpaRepository<StoreProduct, Integer> {
    StoreProduct findByStoreIdAndProductIdAndPriceBD(int storeId, Long product_id, double priceBD);

}
