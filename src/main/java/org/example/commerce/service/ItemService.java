package org.example.commerce.service;

import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.CarCreateRequestDto;
import org.example.commerce.dto.ItemCreateRequestDto;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Item;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemService {

    public Item create(ItemCreateRequestDto itemCreateRequestDto){

        log.info(((CarCreateRequestDto)itemCreateRequestDto).toString());

        return null;
    }
}
