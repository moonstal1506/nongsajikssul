
package com.nongsa.repository;

import com.nongsa.model.shop.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}