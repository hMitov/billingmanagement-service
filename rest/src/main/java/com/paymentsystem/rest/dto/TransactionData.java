package com.paymentsystem.rest.dto;

import com.paymentsystem.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionData {

    private String transactionId;

    private String recipientIban;

    private String senderIban;

    private LocalDateTime dateOfTransaction;

    private double amount;

    private String recipientFirstName;

    private String recipientLastName;

    private Status status;

}
