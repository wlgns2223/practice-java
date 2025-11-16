package org.example.commerce.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@EqualsAndHashCode
public abstract class ItemRequestDto<T extends Item> {

    @NotBlank
    private String name;

    @NotBlank
    private int price;

    @NotBlank
    private int quantity;

    public abstract T toEntity();
}
