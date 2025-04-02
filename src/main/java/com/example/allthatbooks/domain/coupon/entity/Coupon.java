package com.example.allthatbooks.domain.coupon.entity;

import com.example.allthatbooks.domain.common.entity.Timestamped;
import com.example.allthatbooks.domain.coupon.enums.DiscountType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "coupons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String description;

    private String imageUrl;

    private Integer totalQuantity;

    @Column(nullable = false)
//    시간 단위로 만료기한을 정하기로 해서 Integer로 수정했습니다.
    private Integer maintenanceTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiscountType discountType;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private LocalDateTime deletedAt;

}
