package com.example.allthatbooks.domain.coupon.entity;

import com.example.allthatbooks.common.entity.Timestamped;
import com.example.allthatbooks.domain.coupon.enums.CouponStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user_coupon")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_coupon_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponStatus couponStatus;

    private LocalDateTime expirationTime;

    private LocalDateTime deletedAt;
}
