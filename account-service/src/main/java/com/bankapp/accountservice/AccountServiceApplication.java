package com.bankapp.accountservice;

import com.bankapp.accountservice.clients.CustomerRestClient;
import com.bankapp.accountservice.entities.BankAccount;
import com.bankapp.accountservice.enums.AccountStatus;
import com.bankapp.accountservice.enums.AccountType;
import com.bankapp.accountservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.getAllCustomers().forEach(c -> {
                BankAccount bankAccount1 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .createdAt(LocalDate.now())
                        .balance(Math.random()*48532)
                        .currency("MAD")
                        .customerId(c.getId())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .status(AccountStatus.CREATED)
                        .build();
                BankAccount bankAccount2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .createdAt(LocalDate.now())
                        .balance(Math.random()*80000)
                        .currency("EUR")
                        .customerId(c.getId())
                        .type(AccountType.SAVING_ACCOUNT)
                        .status(AccountStatus.ACTIVATED)
                        .build();
                accountRepository.save(bankAccount1);
                accountRepository.save(bankAccount2);
            });
        };
    }
}
