package org.example.commerce.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.ToString;
import org.example.commerce.entity.Item;

@Getter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "dType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarCreateRequestDto.class, name = "CAR"),
        @JsonSubTypes.Type(value = FruitCreateRequestDto.class, name = "FRUIT"),
        @JsonSubTypes.Type(value = ClotheCreateRequestDto.class, name = "CLOTHE")
})
@ToString
public abstract class ItemCreateRequestDto<T extends Item> {

    private String name;
    private int price;
    private int quantity;

    public abstract T toEntity();
}
