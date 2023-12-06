package com.bankapp.accountservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString @Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
