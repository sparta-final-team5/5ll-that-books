package com.example.allthatbooks.domain.cart.repository;

import com.example.allthatbooks.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
