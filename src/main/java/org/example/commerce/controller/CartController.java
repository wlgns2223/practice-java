package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.CartAddDto;
import org.example.commerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @PostMapping("/users/{userId}/item")
    public ResponseEntity<Object> addToCart(@PathVariable Long userId, @RequestBody CartAddDto cartAddDto){

        cartService.addToCart(userId,cartAddDto);
        return ResponseEntity.ok("success");
    }



}
