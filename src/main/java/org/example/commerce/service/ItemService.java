package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.ItemCreateRequestDto;
import org.example.commerce.entity.Item;
import org.example.commerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public Item create(ItemCreateRequestDto<?> itemCreateRequestDto){

        Item item = itemCreateRequestDto.toEntity();


        return itemRepository.save(item);
    }
}
