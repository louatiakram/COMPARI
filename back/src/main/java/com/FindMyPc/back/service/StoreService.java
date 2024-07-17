package com.FindMyPc.back.service;

import com.FindMyPc.back.entity.Store;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<Store> getAllStores();

    Optional<Store> getStoreById(int id);

    Store saveStore(Store store);

    void deleteStore(int id);
}
