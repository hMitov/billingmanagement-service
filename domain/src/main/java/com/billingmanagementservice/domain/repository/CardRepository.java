package com.billingmanagementservice.domain.repository;

import com.billingmanagementservice.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNum);
}
