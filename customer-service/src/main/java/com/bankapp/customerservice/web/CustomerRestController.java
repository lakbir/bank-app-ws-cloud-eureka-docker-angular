package com.bankapp.customerservice.web;

import com.bankapp.customerservice.entities.Customer;
import com.bankapp.customerservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerRestController {
    private CustomerRepository customerRepository;

    @GetMapping()
    public List<Customer> getAllCustomers(){
       return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerRepository.findById(id).get();
    }
}
