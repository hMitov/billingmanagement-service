package com.paymentsystem.rest.dto;

import com.paymentsystem.domain.entity.Currency;
import lombok.Data;

@Data
public class BankAccountData {

    private String iban;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
