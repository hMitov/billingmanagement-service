package com.paymentsystem.billingmanagementservice.domain.adapter;

import com.paymentsystem.billingmanagementservice.domain.entity.BankAccount;
import com.paymentsystem.billingmanagementservice.domain.entity.Transaction;
import com.paymentsystem.billingmanagementservice.domain.value.VBankAccount;
import com.paymentsystem.billingmanagementservice.domain.value.VBankAccountData;
import com.paymentsystem.billingmanagementservice.domain.value.VTransaction;
import com.paymentsystem.billingmanagementservice.domain.value.VTransactionData;
import org.springframework.stereotype.Component;

@Component
public class DomainAdapter {

    public VBankAccount convertBankAccountEntityToValue(BankAccount account) {

        VBankAccount vAccount = new VBankAccount();
        VBankAccountData vData = new VBankAccountData();

        vData.setIban(account.getIban());
        vData.setAvailableAmount(account.getAvailableAmount());
        vData.setCurrency(account.getCurrency());
        vData.setCustomerId(account.getCustomerId());

        vAccount.setId(account.getId());
        vAccount.setData(vData);

        return vAccount;

    }

    public VTransaction convertTransactionEntityToValue(Transaction transaction) {

        VTransaction vTransaction = new VTransaction();
        VTransactionData vData = new VTransactionData();

        vData.setRecipientIban(transaction.getRecipientIban());
        vData.setSenderIban(transaction.getSenderIban());
        vData.setDateOfTransaction(transaction.getDateOfTransaction());
        vData.setAmount(transaction.getAmount());
        vData.setRecipientFirstName(transaction.getRecipientFirstName());
        vData.setRecipientLastName(transaction.getRecipientLastName());
        vData.setStatus(transaction.getStatus());

        vTransaction.setId(transaction.getId());
        vTransaction.setData(vData);

        return vTransaction;

    }

}
