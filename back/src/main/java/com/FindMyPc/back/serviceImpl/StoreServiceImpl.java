package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.entity.Store;
import com.FindMyPc.back.repository.StoreRepository;
import com.FindMyPc.back.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Optional<Store> getStoreById(int id) {
        return storeRepository.findById(id);
    }

    @Override
    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void deleteStore(int id) {
        storeRepository.deleteById(id);
    }
}
