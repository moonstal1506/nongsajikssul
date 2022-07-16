package com.nongsa.repository;

import com.nongsa.model.shop.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}