package com.paymentsystem.domain.repository;

import com.paymentsystem.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardNumber(String cardNum);
}
