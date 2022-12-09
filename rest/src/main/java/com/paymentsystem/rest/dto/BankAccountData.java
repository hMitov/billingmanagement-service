package com.paymentsystem.rest.dto;

import lombok.Data;

import java.util.Currency;

@Data
public class BankAccountData {

    private String iban;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
