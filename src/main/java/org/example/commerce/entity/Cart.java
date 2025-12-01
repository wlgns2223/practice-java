package org.example.commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true,exclude = "items")
public class Cart extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    final private List<CartItem> items = new ArrayList<>();


    public static Cart of(User user){
        Cart cart = new Cart();
        cart.user = user;
        return cart;
    }

    public void addItem(CartItem cartItem){
        cartItem.assignToCart(this);
        items.add(cartItem);
    }

    public Optional<CartItem> findItemById(Long itemId){
        return items.stream().filter(item -> item.getId().equals(itemId)).findFirst();
    }

    public void addOrUpdateItem(Item item, int count){
        findItemById(item.getId())
                .ifPresentOrElse(
                        existing -> existing.changeCount(count),
                        () -> addItem(CartItem.of(item,count))
                );
    }

}
