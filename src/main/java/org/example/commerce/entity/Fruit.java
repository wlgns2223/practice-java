package org.example.commerce.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("FRUIT")
public class Fruit extends Item{
    private String color;
    private String season;

}
