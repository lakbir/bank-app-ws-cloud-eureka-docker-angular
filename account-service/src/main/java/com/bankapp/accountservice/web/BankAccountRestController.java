package com.bankapp.accountservice.web;

import com.bankapp.accountservice.clients.CustomerRestClient;
import com.bankapp.accountservice.entities.BankAccount;
import com.bankapp.accountservice.model.Customer;
import com.bankapp.accountservice.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class BankAccountRestController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    @GetMapping()
    public List<BankAccount> getAllAccounts(){
        List<BankAccount> accountList = accountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.getCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/{id}")
    public BankAccount getBanAccountById(@PathVariable String id){
        BankAccount bankAccount = accountRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }
}
