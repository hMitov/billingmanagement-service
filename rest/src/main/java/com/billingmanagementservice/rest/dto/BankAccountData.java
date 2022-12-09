package com.billingmanagementservice.rest.dto;

import com.billingmanagementservice.domain.entity.Currency;
import lombok.Data;

@Data
public class BankAccountData {

    private String iban;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
