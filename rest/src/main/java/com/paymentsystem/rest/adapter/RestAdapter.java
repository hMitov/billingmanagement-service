package com.paymentsystem.rest.adapter;

import com.paymentsystem.rest.dto.BankAccount;
import com.paymentsystem.rest.dto.BankAccountData;
import com.paymentsystem.rest.dto.Transaction;
import com.paymentsystem.domain.value.VBankAccount;
import com.paymentsystem.domain.value.VBankAccountData;
import com.paymentsystem.domain.value.VTransaction;
import com.paymentsystem.domain.value.VTransactionData;

import com.paymentsystem.rest.dto.TransactionData;
import org.springframework.stereotype.Component;

@Component
public class RestAdapter {

    public VBankAccountData convertBankAccountDtoToValue(BankAccountData data) {

        VBankAccountData vData = new VBankAccountData();

        vData.setIban(data.getIban());
        vData.setAvailableAmount(data.getAvailableAmount());
        vData.setCurrency(data.getCurrency());
        vData.setCustomerId(data.getCustomerId());

        return vData;

    }

    public BankAccount convertBankAccountValueToDto(VBankAccount vAccount) {

        BankAccount bankAccount = new BankAccount();
        BankAccountData data = new BankAccountData();

        data.setIban(vAccount.getData().getIban());
        data.setAvailableAmount(vAccount.getData().getAvailableAmount());
        data.setCurrency(vAccount.getData().getCurrency());
        data.setCustomerId(vAccount.getData().getCustomerId());

        bankAccount.setId(vAccount.getId());
        bankAccount.setData(data);

        return bankAccount;

    }

    public VTransactionData convertTransactionDataDtoToValue(TransactionData data) {

        VTransactionData vData = new VTransactionData();

        vData.setRecipientIban(data.getRecipientIban());
        vData.setTransactionId(data.getTransactionId());
        vData.setSenderIban(data.getSenderIban());
        vData.setDateOfTransaction(data.getDateOfTransaction());
        vData.setAmount(data.getAmount());
        vData.setRecipientFirstName(data.getRecipientFirstName());
        vData.setRecipientLastName(data.getRecipientLastName());
        vData.setStatus(data.getStatus());

        return vData;

    }

    public VTransaction convertTransactionDtoToValue(Transaction transaction) {

        VTransaction vTransaction = new VTransaction();
        VTransactionData vData = new VTransactionData();

        vData.setRecipientIban(transaction.getData().getRecipientIban());
        vData.setSenderIban(transaction.getData().getSenderIban());
        vData.setDateOfTransaction(transaction.getData().getDateOfTransaction());
        vData.setAmount(transaction.getData().getAmount());
        vData.setRecipientFirstName(transaction.getData().getRecipientFirstName());
        vData.setRecipientLastName(transaction.getData().getRecipientLastName());
        vData.setStatus(transaction.getData().getStatus());

        vTransaction.setId(transaction.getId());
        vTransaction.setData(vData);

        return vTransaction;

    }

    public Transaction convertTransactionValueToDto(VTransaction vTransaction) {

        Transaction transaction = new Transaction();
        TransactionData data = new TransactionData();

        data.setRecipientIban(vTransaction.getData().getRecipientIban());
        data.setSenderIban(vTransaction.getData().getSenderIban());
        data.setDateOfTransaction(vTransaction.getData().getDateOfTransaction());
        data.setAmount(vTransaction.getData().getAmount());
        data.setRecipientFirstName(vTransaction.getData().getRecipientFirstName());
        data.setRecipientLastName(vTransaction.getData().getRecipientLastName());
        data.setStatus(vTransaction.getData().getStatus());

        transaction.setId(vTransaction.getId());
        transaction.setData(data);

        return transaction;

    }

}
