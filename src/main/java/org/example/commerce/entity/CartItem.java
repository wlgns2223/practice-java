package org.example.commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.commerce.exception.BadRequestException;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true,exclude = "cart")
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    public static CartItem of(Item item, int count){
        validateCount(count);
        CartItem cartItem = new CartItem();
        item.decreaseStock(count);
        cartItem.item = item;
        cartItem.count = count;

        return cartItem;
    }

    private static void validateCount(int count){
        if(count <= 0){
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
    }

    public void assignToCart(Cart cart){
        this.cart = cart;
    }

    public void changeCount(int newCount){
        int delta = this.count - newCount;

        if(delta > 0){
            item.decreaseStock(delta);
        } else {
            item.increaseStock(delta);
        }
        this.count = newCount;
    }
}
