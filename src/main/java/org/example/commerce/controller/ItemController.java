package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.ItemCreateRequestDto;
import org.example.commerce.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ItemCreateRequestDto itemCreateRequestDto){
        return ResponseEntity.ok(itemService.create(itemCreateRequestDto));

    }

}
