package org.example.commerce.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CLOTHES")
public class Clothes extends Item {

    private String size;
    private String gender;

    @Builder
    private Clothes(String name, int price,int stockQuantity, String size, String gender){
        super(name, price, stockQuantity);
        this.size = size;
        this.gender = gender;
    }
}
