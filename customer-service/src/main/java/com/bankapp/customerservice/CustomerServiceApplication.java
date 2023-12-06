package com.bankapp.customerservice;

import com.bankapp.customerservice.configs.GlobalConfig;
import com.bankapp.customerservice.entities.Customer;
import com.bankapp.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
        return args -> {
            List<Customer> customerList = List.of(
                    Customer.builder()
                            .firstName("Lakbir")
                            .lastName("ABDERRAHIM")
                            .email("lakbir@gmail.com")
                            .build(),
                    Customer.builder()
                            .firstName("Khadija")
                            .lastName("ABDERRAHIM")
                            .email("khadija@gmail.com")
                            .build()
            );
            customerRepository.saveAll(customerList);
        };
    }
}
