package org.example.commerce.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CAR")
public class Car extends Item{

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String type;

    @Builder
    private Car(String name, int price,int stockQuantity,String brand,String type){
        super(name,price,stockQuantity);
        this.brand = brand;
        this.type = type;
    }

    public void updateBrand(String brand){
        this.brand = brand;
    }

    public void updateType(String type){ this.type = type; }
}

