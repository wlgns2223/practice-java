package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.commerce.dto.ItemRequestDto;
import org.example.commerce.dto.ItemUpdateDto;
import org.example.commerce.entity.Item;
import org.example.commerce.exception.NotFoundException;
import org.example.commerce.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item Not Found By ID" + id));
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Long id, ItemUpdateDto itemUpdateDto){
        log.info("dto {}",itemUpdateDto.toString());
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotFoundException("Item Not Found By Id: " + id));

        itemUpdateDto.update(item);

        return item;
    }

    @Transactional
    public void decreaseStock(Long itemId, int quantity){
        Item item = itemRepository.findByIdWithLock(itemId).orElseThrow();
        item.decreaseStock(quantity);

    }
}
