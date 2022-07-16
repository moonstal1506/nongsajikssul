package com.nongsa.repository;

import com.nongsa.model.shop.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}