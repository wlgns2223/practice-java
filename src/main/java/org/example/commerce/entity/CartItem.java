package org.example.commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.commerce.exception.BadRequestException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    @Builder
    public CartItem(Cart cart, Item item,int count){
        this.cart = cart;
        this.item = item;
        this.count = count;
    }

    public void updateCount(int count){
        if(count < 0){
            throw new BadRequestException("수량은 음수가 될 수 없습니다.");
        }
        item.subtractQuantity(count);
        this.count = count;
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
    }


}
