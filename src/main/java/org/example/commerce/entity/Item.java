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

    public void changePrice(int newPrice){
        validatePrice(newPrice);
        this.price = newPrice;
    }

    private void validatePrice(int newPrice){
        if(newPrice < 0){
            throw new IllegalArgumentException("가격음 음수가 될 수 없습니다.");
        }
    }

    public void changeStockQty(int newStockQuantity){
        validateQuantity(newStockQuantity);
        this.stockQuantity = newStockQuantity;

    }



    public void increaseStock(int quantity){
        validateQuantity(quantity);
        stockQuantity = stockQuantity + quantity;
    }

    public void decreaseStock(int quantity){
        validateQuantity(quantity);
        if(stockQuantity < quantity ){
            throw new BadRequestException("재고는 음수가 될 수 없습니다.");
        }
        stockQuantity -= quantity;
    }

    private void validateQuantity(int quantity){
        if(quantity < 0){
            throw new BadRequestException("수량은 음수가 될 수 없습니다.");
        }

    }

}
