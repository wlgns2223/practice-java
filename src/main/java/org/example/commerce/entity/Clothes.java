package org.example.commerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("CLOTHES")
public class Clothes extends Item {

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String gender;

    @Builder
    private Clothes(String name, int price,int stockQuantity, String size, String gender){
        super(name, price, stockQuantity);
        this.size = size;
        this.gender = gender;
    }
}
