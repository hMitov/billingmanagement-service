package com.paymentsystem.billingmanagementservice.domain.repository;

import com.paymentsystem.billingmanagementservice.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
