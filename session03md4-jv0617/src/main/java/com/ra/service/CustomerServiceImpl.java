package com.ra.service;

import com.ra.model.dto.customer.request.CustomerRequestDTO;
import com.ra.model.dto.customer.respone.CustomerResponseDTO;
import com.ra.model.entity.customer.Customer;
import com.ra.repository.CustomerRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository  customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Page<CustomerResponseDTO> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);



        /**
         *
         * search.isEmpty() -> findAll(pageable)
         *
         * else -> findAllByNameContains(search,pageable).
         *
         * nếu search theo nhiều trường thì sẽ phải tạo query cho rõ ràng
         *
         * */

//        List<CustomerResponseDTO> responseDTOS;
//        responseDTOS= customers.stream().map(customer ->
//            CustomerResponseDTO.builder()
//                    .id(customer.getId())
//                    .fullName(customer.getFullName())
//                    .email(customer.getEmail())
//                    .birthday(customer.getBirthday()).build()
//        ).toList();
        return customers.map(item -> CustomerResponseDTO.builder()
                .id(item.getId())
                    .fullName(item.getFullName())
                    .email(item.getEmail())
                    .birthday(item.getBirthday()).build());
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = Customer.builder()
                .fullName(customerRequestDTO.getFullName())
                .email(customerRequestDTO.getEmail())
                .password(customerRequestDTO.getPassword())
                .birthday(customerRequestDTO.getBirthday())
                .build();
        Customer customerNew = customerRepository.save(customer);
        return CustomerResponseDTO.builder()
                .id(customerNew.getId())
                .fullName(customerNew.getFullName())
                .email(customerNew.getEmail())
                .birthday(customerNew.getBirthday()).build();
    }
}
