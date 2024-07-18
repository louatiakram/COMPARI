package com.FindMyPc.back.controller;

import com.FindMyPc.back.entity.Store;
import com.FindMyPc.back.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable int id) {
        Optional<Store> store = storeService.getStoreById(id);
        return store.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable int id, @RequestBody Store storeDetails) {
        Optional<Store> optionalStore = storeService.getStoreById(id);

        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            store.setName(storeDetails.getName());
            store.setLocation(storeDetails.getLocation());
            store.setWebsiteURL(storeDetails.getWebsiteURL());
            store.setLogo(storeDetails.getLogo());
            return ResponseEntity.ok(storeService.saveStore(store));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id) {
        Optional<Store> store = storeService.getStoreById(id);

        if (store.isPresent()) {
            storeService.deleteStore(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
