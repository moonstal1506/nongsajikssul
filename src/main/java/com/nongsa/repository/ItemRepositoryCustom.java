package com.nongsa.repository;

import com.nongsa.dto.shop.ItemSearchDto;
import com.nongsa.model.shop.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
