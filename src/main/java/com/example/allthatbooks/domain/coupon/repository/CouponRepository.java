package com.example.allthatbooks.domain.coupon.repository;

import com.example.allthatbooks.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
