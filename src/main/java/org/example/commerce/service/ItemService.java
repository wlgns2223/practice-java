package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.ItemRequestDto;
import org.example.commerce.entity.Item;
import org.example.commerce.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item create(ItemRequestDto<?> itemRequestDto){

        Item item = itemRequestDto.toEntity();
        return itemRepository.save(item);
    }

    public Item getItem(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }
}
