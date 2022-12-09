package com.billingmanagementservice.domain.value;

import com.billingmanagementservice.domain.entity.CardType;
import lombok.Data;

import java.util.Date;

@Data
public class VCardData {

    private String cardNumber;

    private CardType cardType;

    private Date expirationDate;

    private String cvv;

    private Long bankAccountId;

}
