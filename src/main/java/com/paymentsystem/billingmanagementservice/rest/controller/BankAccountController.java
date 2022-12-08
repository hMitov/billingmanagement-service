package com.paymentsystem.billingmanagementservice.rest.controller;

import com.paymentsystem.billingmanagementservice.rest.dto.BankAccount;
import com.paymentsystem.billingmanagementservice.rest.dto.BankAccountData;
import com.paymentsystem.billingmanagementservice.rest.dto.Transaction;
import com.paymentsystem.billingmanagementservice.rest.dto.TransactionData;
import com.paymentsystem.billingmanagementservice.rest.port.BankAccountRestPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:8082")
@RestController
@RequestMapping(path = "/api")
@Api(tags = {"account"}, value = "BankAccount", produces = APPLICATION_JSON_VALUE)
public class BankAccountController {


    @Autowired
    private BankAccountRestPort bankAccountRestPort;


    @RequestMapping(path = "/bank-account", method = RequestMethod.POST)
    @ApiOperation(value = "Add new bank account", nickname = "createBankAccount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BankAccount.class),
            @ApiResponse(code = 500, message = "Error creating bank account")})
    public BankAccount addBankAccount(@RequestBody BankAccountData data) throws Exception {
        return bankAccountRestPort.addBankAccount(data);
    }


    @RequestMapping(path = "/bank-account/{bankAccountId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update bank account", nickname = "updateBankAccount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BankAccount.class),
            @ApiResponse(code = 500, message = "Error updating bank accounts")})
    public BankAccount updateBankAccount(@PathVariable Long bankAccountId, @RequestBody BankAccountData data) throws Exception {
        return bankAccountRestPort.updateBankAccount(bankAccountId, data);
    }


    @RequestMapping(path = "/bank-account/{bankAccountId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get the bank account with specific id", nickname = "getBankAccountById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BankAccount.class),
            @ApiResponse(code = 400, message = "Invalid ID bank account"),
            @ApiResponse(code = 404, message = "Bank account not found")})
    public BankAccount getBankAccountById(@PathVariable Long bankAccountId) throws Exception {
        return bankAccountRestPort.getBankAccountById(bankAccountId);
    }


    @RequestMapping(path = "/bank-accounts", method = RequestMethod.GET)
    @ApiOperation(value = "Get all bank accounts", nickname = "getAllBankAccounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BankAccount.class),
            @ApiResponse(code = 404, message = "Bank accounts not found"),
            @ApiResponse(code = 500, message = "Error getting bank accounts")})
    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRestPort.getAllBankAccounts();
    }


    @RequestMapping(path = "/bank-account/{bankAccountId}/{customerId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete bank account by id", nickname = "deleteBankAccount")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Error deleting bank accounts")})
    public void deleteBankAccount(@PathVariable Long bankAccountId, @PathVariable Long customerId) {
        bankAccountRestPort.deleteBankAccountForCustomer(bankAccountId, customerId);
    }


    @RequestMapping(path = "/transaction-create", method = RequestMethod.POST)
    @ApiOperation(value = "Create transaction", nickname = "createTransaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Error creating transaction")})
    public Transaction createTransaction(@RequestBody TransactionData data) throws Exception {
        return bankAccountRestPort.createTransaction(data);
    }


    @RequestMapping(path = "/transaction-update/{transactionId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update transaction", nickname = "updateTransaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Error creating transaction")})
    public Transaction updateTransaction(@PathVariable Long transactionId, @RequestBody TransactionData data) throws Exception {
        return bankAccountRestPort.updateTransaction(transactionId, data);
    }

}
