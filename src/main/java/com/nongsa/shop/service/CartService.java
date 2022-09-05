package com.nongsa.shop.service;

import com.nongsa.shop.dto.CartDetailDto;
import com.nongsa.shop.dto.CartItemDto;
import com.nongsa.shop.model.Cart;
import com.nongsa.shop.model.CartItem;
import com.nongsa.shop.model.Item;
import com.nongsa.shop.repository.CartItemRepository;
import com.nongsa.shop.repository.CartRepository;
import com.nongsa.shop.repository.ItemRepository;
import com.nongsa.user.model.User;
import com.nongsa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String username){

        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findByUsername(username);

        Cart cart = cartRepository.findByUserId(user.getId());
        if(cart == null){
            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }

    @Transactional(readOnly = true)
    public List<CartDetailDto> getCartList(String username){

        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

        User user = userRepository.findByUsername(username);
        Cart cart = cartRepository.findByUserId(user.getId());
        if(cart == null){
            return cartDetailDtoList;
        }

        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
        return cartDetailDtoList;
    }
}
