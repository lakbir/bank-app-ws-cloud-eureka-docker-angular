package com.bankapp.accountservice.entities;

import com.bankapp.accountservice.enums.AccountStatus;
import com.bankapp.accountservice.enums.AccountType;
import com.bankapp.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double balance;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Transient
    private Customer customer;
    private Long customerId;
}
