package org.example.commerce.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.example.commerce.entity.Item;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "dType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarUpdateDto.class, name = "CAR"),
        @JsonSubTypes.Type(value = FruitUpdateDto.class, name = "FRUIT"),
        @JsonSubTypes.Type(value = CarUpdateDto.class, name = "CLOTHES")
})
@EqualsAndHashCode
public abstract class ItemUpdateDto {

    private String name;
    private Integer price;
    private Integer stockQuantity;

    protected void updateCommon(Item item){
        if(name != null){
            item.changeName(name);
        }

        if(price != null){
            item.updatePrice(price);
        }

        if(stockQuantity != null){
            item.addStock(stockQuantity);
        }
    }

    public abstract void update(Item item);
}
