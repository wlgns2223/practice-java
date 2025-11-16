package org.example.commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Clothes;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClothesRequestDto extends ItemRequestDto<Clothes> {

    @NotBlank
    private String gender;

    @NotBlank
    private String size;

    @Override
    public Clothes toEntity() {
        return Clothes.builder()
                .name(this.getName())
                .price(this.getPrice())
                .stockQuantity(this.getQuantity())
                .gender(gender)
                .size(size)
                .build();
    }
}
