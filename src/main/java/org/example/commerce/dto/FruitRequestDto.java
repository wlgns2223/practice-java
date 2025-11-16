package org.example.commerce.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.commerce.entity.Fruit;
import org.example.commerce.entity.Item;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FruitRequestDto extends ItemRequestDto<Fruit> {
    private String color;
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
