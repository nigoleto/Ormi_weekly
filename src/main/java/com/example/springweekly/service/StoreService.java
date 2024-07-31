package com.example.springweekly.service;


import com.example.springweekly.domain.Store;
import com.example.springweekly.dto.StoreDTO;
import com.example.springweekly.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService (StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDTO addStore(StoreDTO storeDTO) {
        Store createdStore = storeDTO.convertToStore();
        storeRepository.save(createdStore);
        return StoreDTO.convertToDTO(createdStore);
    }

    public List<StoreDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(StoreDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StoreDTO> updateStore(Long id, StoreDTO storeDTO) {
        Optional<Store> StoreById = storeRepository.findById(id);
        return StoreById.map(store -> {
            store.setName(storeDTO.getName());
            store.setAddress(storeDTO.getAddress());
            store.setPhone(storeDTO.getPhone());
            return StoreDTO.convertToDTO(storeRepository.save(store));
        });
    }

    public boolean deleteStore(Long id) {
        Optional<Store> StoreById = storeRepository.findById(id);
        if (StoreById.isPresent()) {
            storeRepository.delete(StoreById.get());
            return true;
        }
        return false;
    }

}
