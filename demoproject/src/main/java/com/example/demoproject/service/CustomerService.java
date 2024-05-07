package com.example.demoproject.service;

import com.example.demoproject.dto.CustomerDTO;
import com.example.demoproject.dto.PaymentMethodDTO;
import com.example.demoproject.entities.Category;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.entities.PaymentMethod;
import com.example.demoproject.mapper.CustomerMapper;
import com.example.demoproject.mapper.PaymentMethodMapper;
import com.example.demoproject.repository.CustomerRepository;
import com.example.demoproject.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        return customerMapper.toDto(customerList);
    }

    public CustomerDTO findCustomerById(int id) {
        Customer customer= customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return customerMapper.toDto(customer);
    }
}
