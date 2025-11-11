package org.example.commerce.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item extends BaseEntity {

    private String name;
    private int price;
    private int stockQuantity;

}
