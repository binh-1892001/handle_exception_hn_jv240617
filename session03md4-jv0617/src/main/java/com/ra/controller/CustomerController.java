package com.ra.controller;

import com.ra.model.dto.customer.request.CustomerRequestDTO;
import com.ra.model.dto.customer.respone.CustomerResponseDTO;
import com.ra.repository.IProductRepository;
import com.ra.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CustomerController
{
    private final CustomerService customerService;
    private final IProductRepository productRepository;

//    @Autowired
//    public CustomerController(CustomerService customerService, IProductRepository productRepository)
//    {
//        this.customerService = customerService;
//        this.productRepository = productRepository;
//    }


    // Pagebale - đối tượng chưa thông tin phân trang
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> findAll(
            @PageableDefault(page = 0,size = 5,sort = "id",direction = Sort.Direction.ASC) Pageable pageable
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "5") int size,
//            @RequestParam(name = "sort", defaultValue = "id") String sort,
//            @RequestParam(name = "order", defaultValue = "ASC") String order
    )
    {
        // cách 1: @PageableDefault()
        // cách 2: PageRequest

//        Pageable pageable;
//
//        if (order.equals("ASC"))
//        {
//            // theo chiều xuôi
//            pageable = PageRequest.of(page,size, Sort.by(sort).ascending());
//        }
//        else
//        {
//            // theo chiều ngược
//            pageable = PageRequest.of(page,size, Sort.by(sort).descending());
//        }

        Page<CustomerResponseDTO> responseDTOS = customerService.findAll(pageable);
        return new ResponseEntity<>(responseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> save(@Valid @RequestBody CustomerRequestDTO customerRequestDTO)
    {
        CustomerResponseDTO customerResponseDTO = customerService.save(customerRequestDTO);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<?> findAllProducts(
            @PageableDefault(page = 0,size = 5,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "search",defaultValue = "") String search
    ) {
        return ResponseEntity
                .ok()
                .body(productRepository.findAllByCategoryNameContains(search,pageable));
    }

}
