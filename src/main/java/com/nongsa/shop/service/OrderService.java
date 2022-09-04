package com.nongsa.shop.service;

import com.nongsa.shop.dto.OrderDto;
import com.nongsa.shop.model.Item;
import com.nongsa.shop.model.Order;
import com.nongsa.shop.model.OrderItem;
import com.nongsa.shop.repository.ItemImgRepository;
import com.nongsa.shop.repository.ItemRepository;
import com.nongsa.shop.repository.OrderRepository;
import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String username) {

        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findByUsername(username);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);
        Order order = Order.createOrder(user, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }
}