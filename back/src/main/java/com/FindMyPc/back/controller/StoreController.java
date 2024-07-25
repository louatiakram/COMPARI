package com.FindMyPc.back.controller;

import com.FindMyPc.back.ResponseDto.StoreResponseDto;
import com.FindMyPc.back.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public ResponseEntity<List<StoreResponseDto>> getAllStores() {
        List<StoreResponseDto> stores = storeService.getAllStores();
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreResponseDto> getStoreById(@PathVariable int id) {
        Optional<StoreResponseDto> store = storeService.getStoreById(id);
        return store.map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody StoreResponseDto storeResponseDto) {
        StoreResponseDto savedStore = storeService.saveStore(storeResponseDto);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreResponseDto> updateStore(@PathVariable int id, @RequestBody StoreResponseDto storeResponseDto) {
        storeResponseDto.setStoreID(id);
        StoreResponseDto updatedStore = storeService.updateStore(storeResponseDto);
        if (updatedStore != null) {
            return new ResponseEntity<>(updatedStore, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
