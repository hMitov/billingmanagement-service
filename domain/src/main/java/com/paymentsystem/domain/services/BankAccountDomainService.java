package com.paymentsystem.domain.services;

import com.paymentsystem.domain.adapter.DomainAdapter;
import com.paymentsystem.domain.entity.BankAccount;
import com.paymentsystem.domain.entity.Status;
import com.paymentsystem.domain.entity.Transaction;
import com.paymentsystem.domain.repository.BankAccountRepository;
import com.paymentsystem.domain.repository.CardRepository;
import com.paymentsystem.domain.repository.TransactionRepository;
import com.paymentsystem.domain.value.VBankAccount;
import com.paymentsystem.domain.value.VBankAccountData;
import com.paymentsystem.domain.value.VTransaction;
import com.paymentsystem.domain.value.VTransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankAccountDomainService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private DomainAdapter domainAdapter;


    public VBankAccount addBankAccount(VBankAccountData data) throws Exception {

        BankAccount bankAccount = bankAccountRepository.findByIban(data.getIban())
                .orElseThrow(() -> new Exception("No such account"));

        if (bankAccount != null) {
            throw new Exception("Already exists in db");
        }

        bankAccount = new BankAccount();

        bankAccount.setCurrency(data.getCurrency());
        bankAccount.setIban(data.getIban());
        bankAccount.setAvailableAmount(data.getAvailableAmount());
        bankAccount.setCustomerId(data.getCustomerId());

        return domainAdapter.convertBankAccountEntityToValue(bankAccountRepository.save(bankAccount));

    }

    public VBankAccount updateBankAccount(Long bankAccountId, VBankAccountData data) throws Exception {

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).isEmpty() ?
                new BankAccount() : bankAccountRepository.findById(bankAccountId).get();

        bankAccount.setCurrency(data.getCurrency());
        bankAccount.setIban(data.getIban());
        bankAccount.setAvailableAmount(data.getAvailableAmount());
        bankAccount.setCustomerId(data.getCustomerId());

        return domainAdapter.convertBankAccountEntityToValue(bankAccountRepository.save(bankAccount));

    }

    public List<VBankAccount> getAllBankAccounts() {

        return bankAccountRepository.findAll().stream().map(this.domainAdapter::convertBankAccountEntityToValue).collect(Collectors.toList());

    }

    public VBankAccount getBankAccountById(Long bankAccountId) throws Exception {
        System.out.println("COntroller works");
        return domainAdapter.convertBankAccountEntityToValue(bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new Exception("Not such an account")));

    }

    public void deleteBankAccountForCustomer(Long bankAccountId, Long customerId) {

        bankAccountRepository.deleteByIdAndCustomerId(bankAccountId, customerId);

    }

    public VTransaction createTransaction(VTransactionData data) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setRecipientIban(data.getRecipientIban());
        transaction.setSenderIban(data.getSenderIban());
        transaction.setDateOfTransaction(data.getDateOfTransaction());
        transaction.setAmount(data.getAmount());
        transaction.setRecipientFirstName(data.getRecipientFirstName());
        transaction.setRecipientLastName(data.getRecipientLastName());
        transaction.setStatus(data.getStatus());

        BankAccount loadedBankAccount = bankAccountRepository.findByIban(data.getSenderIban())
                .orElseThrow(() -> new Exception("No such account"));
        loadedBankAccount.setAvailableAmount(loadedBankAccount.getAvailableAmount() - data.getAmount());


        return domainAdapter.convertTransactionEntityToValue(transactionRepository.save(transaction));

    }


    public VTransaction processTransaction(VTransaction vTransaction) throws Exception {

        BankAccount bankAccount = bankAccountRepository.findByIban(vTransaction.getData().getSenderIban())
                .orElseThrow(() -> new Exception("No such account"));

        if (checkIfEnoughMoneyInBankAccount(vTransaction.getData().getAmount(), bankAccount)) {

            vTransaction.getData().setRecipientIban(vTransaction.getData().getRecipientIban());
            vTransaction.getData().setTransactionId(UUID.randomUUID().toString());
            vTransaction.getData().setSenderIban(vTransaction.getData().getSenderIban());
            vTransaction.getData().setDateOfTransaction(vTransaction.getData().getDateOfTransaction());
            vTransaction.getData().setAmount(vTransaction.getData().getAmount());
            vTransaction.getData().setRecipientFirstName(vTransaction.getData().getRecipientFirstName());
            vTransaction.getData().setRecipientLastName(vTransaction.getData().getRecipientLastName());
            vTransaction.getData().setStatus(Status.PROCESSED);

        } else {
            throw new Exception("Not enough money in bank account");
        }

        return vTransaction;

    }

    public boolean checkIfEnoughMoneyInBankAccount(double requestedMoney, BankAccount bankAccount) {

        return bankAccount.getAvailableAmount() >= requestedMoney;

    }


    public VTransaction updateTransaction(Long transactionId, VTransactionData data) throws Exception {

        Transaction transaction =
                transactionRepository.findById(transactionId).orElseThrow(() -> new Exception("No such a transaction"));

        if (data.getStatus().equals(Status.PROCESSED) && data.getTransactionId() != null) {

            transaction.setTransactionId(data.getTransactionId());
            transaction.setRecipientIban(data.getRecipientIban());
            transaction.setSenderIban(data.getSenderIban());
            transaction.setDateOfTransaction(data.getDateOfTransaction());
            transaction.setAmount(data.getAmount());
            transaction.setRecipientFirstName(data.getRecipientFirstName());
            transaction.setRecipientLastName(data.getRecipientLastName());
            transaction.setStatus(Status.COMPLETED);

        }

        BankAccount loadedBankAccount = bankAccountRepository
                .findByIban(checkIfTransactionCompleted(transaction, data) ? data.getRecipientIban() : data.getSenderIban())
                .orElseThrow(() -> new Exception("No such account"));
        loadedBankAccount.setAvailableAmount(loadedBankAccount.getAvailableAmount() + data.getAmount());

        bankAccountRepository.save(loadedBankAccount);

        Transaction savedTransaction =
                transaction.getTransactionId() != null && transaction.getStatus().equals(Status.COMPLETED) ?
                        transactionRepository.save(transaction) : new Transaction();

        return domainAdapter.convertTransactionEntityToValue(savedTransaction);

    }

    public boolean checkIfTransactionCompleted(Transaction transaction, VTransactionData data) {

        return transaction.getTransactionId() != null && transaction.getStatus().equals(Status.COMPLETED);

    }

}
