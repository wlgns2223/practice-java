package org.example.commerce.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Item;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarUpdateDto extends ItemUpdateDto{

    private String brand;
    private String type;

    @Override
    public void update(Item item) {
        this.updateCommon(item);
        if(!(item instanceof Car car)){
            throw new IllegalArgumentException("잘 못된 엔터티입니다.");
        }
        car.updateBrand(brand);
        car.updateType(type);
    }
}
