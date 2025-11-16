package org.example.commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.commerce.entity.Fruit;
import org.example.commerce.entity.Item;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FruitRequestDto extends ItemRequestDto<Fruit> {

    @NotBlank
    private String color;

    @NotBlank
    private String season;


    @Override
    public Fruit toEntity() {
        return Fruit.builder()
                .name(this.getName())
                .price(this.getPrice())
                .stockQuantity(this.getQuantity())
                .color(color)
                .season(season)
                .build();
    }
}
