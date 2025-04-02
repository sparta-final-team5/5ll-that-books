package com.example.allthatbooks.domain.coupon.repository;

import com.example.allthatbooks.domain.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
}
