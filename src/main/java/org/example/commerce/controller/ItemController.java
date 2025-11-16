package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.ItemRequestDto;
import org.example.commerce.dto.ItemUpdateDto;
import org.example.commerce.entity.Item;
import org.example.commerce.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<Item> create(@RequestBody ItemRequestDto<?> itemRequestDto){
        Item item = itemService.create(itemRequestDto);
        return ResponseEntity
                .created(URI.create("/item/" + item.getId()))
                .body(item);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItem(id));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody ItemUpdateDto itemUpdateDto){
        return ResponseEntity.ok(itemService.update(id, itemUpdateDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        itemService.delete(id);
        return ResponseEntity.ok("success");

    }



}
