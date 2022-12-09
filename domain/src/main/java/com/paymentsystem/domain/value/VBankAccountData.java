package com.paymentsystem.domain.value;

import lombok.Data;

import java.util.Currency;

@Data
public class VBankAccountData {

    private String iban;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
