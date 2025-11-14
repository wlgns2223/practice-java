package org.example.commerce.dto;

import lombok.*;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Item;


@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarCreateRequestDto extends ItemCreateRequestDto<Item> {
    private String brand;
    private String type;

    @Override
    public Car toEntity() {
        return Car.create(
                this.getName(),
                this.getPrice(),
                this.getQuantity(),
                this.brand,
                this.type
        );
    }
}
