package com.ra.service;

import com.ra.model.dto.customer.request.CustomerRequestDTO;
import com.ra.model.dto.customer.respone.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Page<CustomerResponseDTO> findAll(Pageable pageable);
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
}
