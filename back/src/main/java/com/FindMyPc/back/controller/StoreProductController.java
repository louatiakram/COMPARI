package com.FindMyPc.back.controller;

import com.FindMyPc.back.ResponseDto.StoreProductResponseDto;
import com.FindMyPc.back.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/store-products")
public class StoreProductController {

    @Autowired
    private StoreProductService storeProductService;

    @GetMapping
    public ResponseEntity<List<StoreProductResponseDto>> getAllStoreProducts() {
        List<StoreProductResponseDto> storeProducts = storeProductService.getAllAffected();
        return new ResponseEntity<>(storeProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreProductResponseDto> getStoreProductById(@PathVariable int id) {
        Optional<StoreProductResponseDto> storeProduct = storeProductService.getAffectedById(id);
        return storeProduct.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StoreProductResponseDto> createStoreProduct(@RequestBody StoreProductResponseDto storeProductResponseDto) {
        StoreProductResponseDto savedStoreProduct = storeProductService.saveAffected(storeProductResponseDto);
        return new ResponseEntity<>(savedStoreProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreProductResponseDto> updateStoreProduct(@PathVariable int id, @RequestBody StoreProductResponseDto storeProductResponseDto) {
        storeProductResponseDto.setId(id);
        StoreProductResponseDto updatedStoreProduct = storeProductService.updateAffected(storeProductResponseDto);
        if (updatedStoreProduct != null) {
            return new ResponseEntity<>(updatedStoreProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStoreProduct(@PathVariable int id) {
        storeProductService.deleteAffected(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<StoreProductResponseDto>> getStoreProductsPaginated(@PageableDefault(size = 10) Pageable pageable) {
        Page<StoreProductResponseDto> storeProductPage = storeProductService.getAffectedPaginated(pageable.getPageNumber(), pageable.getPageSize());
        return new ResponseEntity<>(storeProductPage, HttpStatus.OK);
    }
}
