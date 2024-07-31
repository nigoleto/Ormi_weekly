package com.example.springweekly.dto;

import com.example.springweekly.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {
    private String name;
    private String address;
    private String phone;

    public static StoreDTO convertToDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName(store.getName());
        storeDTO.setAddress(store.getAddress());
        storeDTO.setPhone(store.getPhone());
        return storeDTO;
    }

    public Store convertToStore() {
        return new Store().builder()
                .name(this.name)
                .address(this.address)
                .phone(this.phone)
                .build();
    }
}
