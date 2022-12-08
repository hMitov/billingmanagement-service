package com.paymentsystem.billingmanagementservice.domain.value;

import com.paymentsystem.billingmanagementservice.domain.entity.Currency;
import lombok.Data;

@Data
public class VBankAccountData {

    private String iban;

    private double availableAmount;

    private Currency currency;

    private Long customerId;

}
