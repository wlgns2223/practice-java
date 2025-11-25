package org.example.commerce.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CartAddDto {

    private Long itemId;
    private int count;
}
