package org.example.commerce.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.commerce.entity.Car;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarCreateRequestDto extends ItemCreateRequestDto<CarCreateRequestDto> {
    private String brand;
    private String type;

    @Override
    CarCreateRequestDto toEntity() {}
}
