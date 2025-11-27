package org.example.commerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.example.commerce.exception.BadRequestException;

@Entity
@Getter
@Builder
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

    public void updateCount(int newCount){
        if(newCount < 0){
            throw new BadRequestException("수량은 음수가 될 수 없습니다.");
        }
        int delta = newCount - count;
        if(delta > 0) item.subtractQuantity(delta);
        else item.addQuantity(-delta);

        this.count = newCount;
    }

    public void changeCart(Cart cart) {
        this.cart = cart;
    }


}
