package com.nongsa.model.shop;

import com.nongsa.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cartItemId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cartId")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    private int count;
}