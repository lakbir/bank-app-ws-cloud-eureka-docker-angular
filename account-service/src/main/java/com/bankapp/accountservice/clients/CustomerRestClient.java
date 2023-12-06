package com.bankapp.accountservice.clients;

import com.bankapp.accountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultAllCustomers")
    List<Customer> getAllCustomers();

    default Customer getDefaultCustomer(Long id, Exception exception){
        Customer customer = Customer.builder()
                .email("not available")
                .firstName("not available")
                .lastName("not available")
                .build();
        return customer;
    }

    default List<Customer> getDefaultAllCustomers(Exception exception){
        return List.of();
    }
}
