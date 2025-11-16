package org.example.commerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Item;


@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarRequestDto extends ItemRequestDto<Car> {

    @NotBlank
    private String brand;

    @NotBlank
    private String type;

    @Override
    public Car toEntity() {
        return Car.builder()
                .name(this.getName())
                .price(this.getPrice())
                .stockQuantity(this.getQuantity())
                .brand(brand)
                .type(type)
                .build();

    }
}
