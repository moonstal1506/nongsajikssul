package com.nongsa.model.shop;

import com.nongsa.model.BaseEntity;
import com.nongsa.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    public static Cart createCart(User user){
        Cart cart = new Cart();
        cart.setUser(user);
        return cart;
    }
}