package org.example.commerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Builder
    public Cart(User user){
        this.user = user;
    }

    public void addItem(CartItem item){
        item.changeCart(this);
        items.add(item);
    }

}
