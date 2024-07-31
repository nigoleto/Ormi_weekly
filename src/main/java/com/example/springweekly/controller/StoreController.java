package com.example.springweekly.controller;

import com.example.springweekly.domain.Store;
import com.example.springweekly.dto.StoreDTO;
import com.example.springweekly.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreDTO> addStore(@RequestBody StoreDTO storeDTO) {
        StoreDTO createdStoreDTO = storeService.addStore(storeDTO);
        return ResponseEntity.ok(createdStoreDTO);
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<StoreDTO> storeDTOList = storeService.getAllStores();
        return ResponseEntity.ok(storeDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        return storeService.updateStore(id, storeDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public boolean deleteStore(@PathVariable Long id) {
        return storeService.deleteStore(id);
    }

}
