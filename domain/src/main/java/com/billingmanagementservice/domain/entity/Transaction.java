package com.billingmanagementservice.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "recipient_iban")
    private String recipientIban;

    @Column(name = "sender_iban")
    private String senderIban;

    @Column(name = "date_of_transaction")
    private LocalDateTime dateOfTransaction;

    @Column(name = "amount")
    private double amount;

    @Column(name = "recipient_first_name")
    private String recipientFirstName;

    @Column(name = "recipient_last_name")
    private String recipientLastName;

    @Enumerated(EnumType.STRING)
    private Status status;

}
