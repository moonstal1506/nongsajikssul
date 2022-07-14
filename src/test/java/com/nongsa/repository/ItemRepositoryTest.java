package com.nongsa.repository;

import com.nongsa.constant.ItemSellStatus;
import com.nongsa.model.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest(){
        //given
        Item item = new Item();
        item.setItemName("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        Item savedItem = itemRepository.save(item);

        //when
        Item findItem = itemRepository.findById(savedItem.getId()).get();

        //then
        Assertions.assertThat(findItem.getItemName()).isEqualTo(item.getItemName());
    }
}