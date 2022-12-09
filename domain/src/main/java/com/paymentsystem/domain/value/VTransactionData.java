package com.paymentsystem.domain.value;

import com.paymentsystem.domain.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VTransactionData {

    private String transactionId;

    private String recipientIban;

    private String senderIban;

    private LocalDateTime dateOfTransaction;

    private double amount;

    private String recipientFirstName;

    private String recipientLastName;

    private Status status;

}
