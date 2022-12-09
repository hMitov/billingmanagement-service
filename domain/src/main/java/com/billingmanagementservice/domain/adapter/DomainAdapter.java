package com.billingmanagementservice.domain.adapter;

import com.billingmanagementservice.domain.entity.Transaction;
import com.billingmanagementservice.domain.entity.BankAccount;
import com.billingmanagementservice.domain.value.VBankAccount;
import com.billingmanagementservice.domain.value.VBankAccountData;
import com.billingmanagementservice.domain.value.VTransaction;
import com.billingmanagementservice.domain.value.VTransactionData;
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
