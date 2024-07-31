package com.example.springweekly.service;

import com.example.springweekly.domain.Customer;
import com.example.springweekly.dto.CustomerDTO;
import com.example.springweekly.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerDTO.convertToEntity();
        customerRepository.save(customer);
        return customerDTO;
    }

    public List<CustomerDTO> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(CustomerDTO::convertToDTO)
                .collect(Collectors.toList());
    }


    public Optional<CustomerDTO> updateCustomer(Long id, CustomerDTO updateCustomerDTO) {
        Optional<Customer> customerById = customerRepository.findById(id);
        return customerById.map(c -> {
            c.setName(updateCustomerDTO.getName());
            c.setAddress(updateCustomerDTO.getAddress());
            c.setPhone(updateCustomerDTO.getPhone());
            return CustomerDTO.convertToDTO(customerRepository.save(c));
        });
    }

    public boolean deleteCustomer(Long id) {
        return customerRepository.findById(id)
                .map(c -> {
                    customerRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
