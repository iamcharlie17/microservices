package com.example.customer_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer_service.models.Customer;
import com.example.customer_service.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public Customer save(@RequestBody Customer customer) {
        
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable String id) {
        return customerService.findById(id);
    }
}
