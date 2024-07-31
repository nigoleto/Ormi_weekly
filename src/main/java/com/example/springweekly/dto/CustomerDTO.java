package com.example.springweekly.dto;

import com.example.springweekly.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String name;
    private String phone;
    private String address;

    public Customer convertToEntity() {
        Customer customer = new Customer().builder()
                .name(this.name)
                .phone(this.phone)
                .address(this.address)
                .build();
        return customer;
    }

    public static CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(customer.getAddress());
        return customerDTO;
    }
}
