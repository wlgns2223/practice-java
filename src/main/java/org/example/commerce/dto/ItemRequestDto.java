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
        @JsonSubTypes.Type(value = CarRequestDto.class, name = "CAR"),
        @JsonSubTypes.Type(value = FruitRequestDto.class, name = "FRUIT"),
        @JsonSubTypes.Type(value = ClothesRequestDto.class, name = "CLOTHES")
})
@ToString
public abstract class ItemRequestDto<T extends Item> {

    private String name;
    private int price;
    private int quantity;

    public abstract T toEntity();
}
