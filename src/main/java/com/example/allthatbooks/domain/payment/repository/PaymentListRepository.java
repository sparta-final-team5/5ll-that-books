package com.example.allthatbooks.domain.payment.repository;

import com.example.allthatbooks.domain.payment.entity.PaymentList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentListRepository extends JpaRepository<PaymentList, Long> {
}
