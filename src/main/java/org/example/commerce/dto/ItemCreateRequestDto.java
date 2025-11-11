package org.example.commerce.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import org.example.commerce.entity.Item;

@Data
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
public abstract class ItemCreateRequestDto<T extends Item> {

    private String name;
    private int price;
    private int quantity;

    abstract T toEntity();

}
