package com.billingmanagementservice.domain.repository;

import com.billingmanagementservice.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
