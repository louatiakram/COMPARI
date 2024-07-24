package com.FindMyPc.back.serviceImpl;

import com.FindMyPc.back.ResponseDto.StoreResponseDto;
import com.FindMyPc.back.entity.Store;
import com.FindMyPc.back.repository.StoreRepository;
import com.FindMyPc.back.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StoreResponseDto> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(store -> modelMapper.map(store, StoreResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StoreResponseDto> getStoreById(int id) {
        return storeRepository.findById(id)
                .map(store -> modelMapper.map(store, StoreResponseDto.class));
    }

    @Override
    public StoreResponseDto saveStore(StoreResponseDto storeResponseDto) {
        Store store = modelMapper.map(storeResponseDto, Store.class);
        Store savedStore = storeRepository.save(store);
        return modelMapper.map(savedStore, StoreResponseDto.class);
    }

    @Override
    public StoreResponseDto updateStore(StoreResponseDto storeResponseDto) {
        if (storeRepository.existsById(storeResponseDto.getStoreID())) {
            Store store = modelMapper.map(storeResponseDto, Store.class);
            Store updatedStore = storeRepository.save(store);
            return modelMapper.map(updatedStore, StoreResponseDto.class);
        }
        return null; // or throw an exception if appropriate
    }

    @Override
    public void deleteStore(int id) {
        if (storeRepository.existsById(id)) {
            storeRepository.deleteById(id);
        }
        // Handle the case where the store does not exist
    }
}
