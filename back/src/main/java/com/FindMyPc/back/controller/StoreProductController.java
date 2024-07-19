package com.FindMyPc.back.controller;


import com.FindMyPc.back.entity.StoreProduct;
import com.FindMyPc.back.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/storeproduct")
public class StoreProductController {

    @Autowired
    private StoreProductService storeProductService;

    @GetMapping
    public List<StoreProduct> getAllAffected() {
        return storeProductService.getAllAffected();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreProduct> getAffectedById(@PathVariable int id) {
        Optional<StoreProduct> affected = storeProductService.getAffectedById(id);
        return affected.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public StoreProduct createAffected(@RequestBody StoreProduct storeProduct) {
        return storeProductService.saveAffected(storeProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreProduct> updateAffected(@PathVariable int id, @RequestBody StoreProduct storeProductDetails) {
        Optional<StoreProduct> optionalAffected = storeProductService.getAffectedById(id);

        if (optionalAffected.isPresent()) {
            StoreProduct storeProduct = optionalAffected.get();
            storeProduct.setPriceBD(storeProductDetails.getPriceBD());
            storeProduct.setPriceAD(storeProductDetails.getPriceAD());
            storeProduct.setProduct(storeProductDetails.getProduct());
            storeProduct.setStore(storeProductDetails.getStore());
            return ResponseEntity.ok(storeProductService.saveAffected(storeProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAffected(@PathVariable int id) {
        Optional<StoreProduct> affected = storeProductService.getAffectedById(id);

        if (affected.isPresent()) {
            storeProductService.deleteAffected(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginated")
    public Page<StoreProduct> getAffectedPaginated(@RequestParam int page, @RequestParam int size) {
        return storeProductService.getAffectedPaginated(page, size);
    }
}
