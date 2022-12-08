package com.paymentsystem.billingmanagementservice.rest.port;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.paymentsystem.billingmanagementservice.domain.services.BankAccountDomainService;
import com.paymentsystem.billingmanagementservice.domain.value.VBankAccountData;
import com.paymentsystem.billingmanagementservice.domain.value.VTransaction;
import com.paymentsystem.billingmanagementservice.domain.value.VTransactionData;
import com.paymentsystem.billingmanagementservice.rest.adapter.RestAdapter;
import com.paymentsystem.billingmanagementservice.rest.dto.BankAccount;
import com.paymentsystem.billingmanagementservice.rest.dto.BankAccountData;
import com.paymentsystem.billingmanagementservice.rest.dto.Transaction;
import com.paymentsystem.billingmanagementservice.rest.dto.TransactionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Component
public class BankAccountRestPort {

    @Autowired
    private RestAdapter restAdapter;

    @Autowired
    private BankAccountDomainService bankAccountDomainService;

    @Autowired
    private WebClient webClient;


    public BankAccount addBankAccount(BankAccountData dataDto) throws Exception {

        VBankAccountData vData = restAdapter.convertBankAccountDtoToValue(dataDto);
        return restAdapter.convertBankAccountValueToDto(bankAccountDomainService.addBankAccount(vData));

    }


    public BankAccount updateBankAccount(Long bankAccountId, BankAccountData dataDto) throws Exception {

        VBankAccountData vData = restAdapter.convertBankAccountDtoToValue(dataDto);
        return restAdapter.convertBankAccountValueToDto(bankAccountDomainService.updateBankAccount(bankAccountId, vData));

    }

    public BankAccount getBankAccountById(Long bankAccountId) throws Exception {

        return restAdapter.convertBankAccountValueToDto(bankAccountDomainService.getBankAccountById(bankAccountId));

    }

    public List<BankAccount> getAllBankAccounts() {

        return bankAccountDomainService.getAllBankAccounts().stream()
                .map(this.restAdapter::convertBankAccountValueToDto)
                .collect(Collectors.toList());

    }

    public void deleteBankAccountForCustomer(Long bankAccountId, Long customerId) {

        bankAccountDomainService.deleteBankAccountForCustomer(bankAccountId, customerId);

    }

    public Transaction createTransaction(TransactionData data) throws Exception {

        VTransactionData vData = restAdapter.convertTransactionDataDtoToValue(data);

        Transaction createdTransaction = restAdapter
                .convertTransactionValueToDto(bankAccountDomainService.createTransaction(vData));

        Transaction processedTransaction = processTransaction(restAdapter.convertTransactionDtoToValue(createdTransaction));

        webClient.put().uri("/transaction-update/" + processedTransaction.getId())
                .body(Mono.just(processedTransaction.getData()), TransactionData.class)
                .retrieve().bodyToMono(Transaction.class)
                .block();

        return createdTransaction;

    }

    private Transaction processTransaction(VTransaction vTransaction) throws Exception {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

        ScheduledFuture<VTransaction> scheduledFuture = scheduledExecutorService.schedule(new Callable<VTransaction>() {
            public VTransaction call() throws Exception {
                return bankAccountDomainService.processTransaction(vTransaction);
            }
        }, 5, TimeUnit.SECONDS);

        return new ObjectMapper().registerModule(new JavaTimeModule())
                .convertValue(scheduledFuture.get(), Transaction.class);

    }

    public Transaction updateTransaction(Long transactionId, TransactionData data) throws Exception {

        VTransactionData vData = restAdapter.convertTransactionDataDtoToValue(data);
        return restAdapter.convertTransactionValueToDto(bankAccountDomainService.updateTransaction(transactionId, data));

    }
}
