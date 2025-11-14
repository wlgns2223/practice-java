package org.example.commerce.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CAR")
public class Car extends Item{
    private String brand;
    private String type;

    public static Car create(String name, int price, int stockQuantity, String brand,String type){
        return Car.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .brand(brand)
                .type(type)
                .build();
    }
}

