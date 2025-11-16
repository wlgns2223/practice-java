package org.example.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("FRUIT")
public class Fruit extends Item{

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String season;

    @Builder
    private Fruit(String name, int price,int stockQuantity,String color,String season){
        super(name,price,stockQuantity);
        this.season = season;
        this.color = color;
    }

}
