package com.billingmanagementservice.rest.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CardData {

    private String cardNumber;

    private CardType cardType;

    private Date expirationDate;

    private String cvv;

    private Long bankAccountId;

}
