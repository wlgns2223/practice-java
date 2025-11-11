package org.example.commerce.dto;

import lombok.Getter;
import org.example.commerce.entity.Item;

@Getter
public class FruitCreateRequestDto extends Item {
    private String color;
    private String season;
}
