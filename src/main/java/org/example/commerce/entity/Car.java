package org.example.commerce.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CAR")
public class Car extends Item{
    private String brand;
    private String type;

    @Builder
    private Car(String name, int price,int stockQuantity,String brand,String type){
        super(name,price,stockQuantity);
        this.brand = brand;
        this.type = type;
    }
}

