package com.paymentsystem.billingmanagementservice.domain.repository;

import com.paymentsystem.billingmanagementservice.domain.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByIban(String iban);

    void deleteByIdAndCustomerId(Long id, Long customerId);

    BankAccount findByCustomerId(Long customerId);
}
