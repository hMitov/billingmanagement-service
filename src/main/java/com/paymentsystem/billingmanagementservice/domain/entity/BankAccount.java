package com.paymentsystem.billingmanagementservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iban;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bankAccount")
    private List<Card> card;

    private double availableAmount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Long customerId;

}
