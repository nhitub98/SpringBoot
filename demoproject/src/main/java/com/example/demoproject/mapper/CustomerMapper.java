package com.example.demoproject.mapper;

import com.example.demoproject.dto.CustomerDTO;
import com.example.demoproject.entities.Customer;
import com.example.demoproject.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper implements EntityMapper<Customer, CustomerDTO> {
    @Override
    public Customer toEntity(CustomerDTO dto) {
//            Timestamp createdDate = DateUtils.stringToTimestamp(dto.getCreatedDate());
            return Customer.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .username(dto.getUsername())
                    .address(dto.getAddress())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .createdDate(dto.getCreatedDate())
                    .isactive(dto.getIsactive())
                    .build();
        }

    @Override
    public CustomerDTO toDto(Customer entity) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String createdDateString = dateFormat.format(entity.getCreatedDate());
        return CustomerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .createdDate(entity.getCreatedDate())
                .isactive(entity.getIsactive())
                .build();
    }

    @Override
    public List<Customer> toEntity(List<CustomerDTO> dto) {
        List<Customer> customerList = new ArrayList<>();
        dto.forEach(customerDTO -> customerList.add(toEntity(customerDTO)));
        return customerList;
    }

    @Override
    public List<CustomerDTO> toDto(List<Customer> entity) {
        List<CustomerDTO> dtos = new ArrayList<>();
        entity.forEach(customer -> dtos.add(toDto(customer)));
        return dtos;
    }
}
