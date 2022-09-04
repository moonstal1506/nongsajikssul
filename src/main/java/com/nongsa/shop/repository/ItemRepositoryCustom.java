package com.nongsa.shop.repository;

import com.nongsa.shop.dto.ItemSearchDto;
import com.nongsa.shop.dto.MainItemDto;
import com.nongsa.shop.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
