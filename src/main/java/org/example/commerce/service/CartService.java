package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.CartAddDto;
import org.example.commerce.entity.Cart;
import org.example.commerce.entity.Item;
import org.example.commerce.entity.User;
import org.example.commerce.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {
    final private CartRepository cartRepository;
    final private UserService userService;
    final private ItemService itemService;

    //TODO 동시성
    @Transactional
    public Cart addToCart(Long userId, CartAddDto cartAddDto){

        Cart cart = cartRepository.findById(userId).orElseGet(() -> {
            User user = userService.findUserById(userId);
            return Cart.of(user);
        });

        Item item = itemService.getItem(cartAddDto.getItemId());
        cart.addOrUpdateItem(item,cartAddDto.getCount());

        return cartRepository.save(cart);
    }

}
