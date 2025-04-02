package com.example.allthatbooks.domain.payment.repository;

import com.example.allthatbooks.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
