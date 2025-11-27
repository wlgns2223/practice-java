package org.example.commerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.CartAddDto;
import org.example.commerce.entity.Cart;
import org.example.commerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @PutMapping("/users/{userId}/item")
    public ResponseEntity<Cart> addToCart(@PathVariable Long userId, @RequestBody CartAddDto cartAddDto){

        return ResponseEntity.ok(cartService.addToCart(userId,cartAddDto));
    }



}
