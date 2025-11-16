package org.example.commerce.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.commerce.exception.BadRequestException;
import org.springframework.util.StringUtils;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@ToString
public abstract class Item extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockQuantity;

    protected Item(String name,int price,int stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void changeName(String name){
        if(!StringUtils.hasText(name)) return;

        this.name = name;
    }

    public void addStock(int newStock){
        int nextStock = stockQuantity + newStock;
        if(nextStock < 0){
            throw new BadRequestException("총 재고는 음수가 될 수 없습니다.");
        }
        stockQuantity = nextStock;
    }

    public void updatePrice(int newPrice){
        if(newPrice < 0){
            throw new BadRequestException("가격은 음수가 될 수 없습니다.");
        }
        price = newPrice;
    }

}
