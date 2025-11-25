package org.example.commerce.service;

import lombok.RequiredArgsConstructor;
import org.example.commerce.dto.CartAddDto;
import org.example.commerce.entity.Cart;
import org.example.commerce.entity.CartItem;
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

    //TODO 멱등성, 재고관리
    @Transactional
    public void addToCart(Long userId, CartAddDto cartAddDto){

        Cart cart = cartRepository.findById(userId).orElse(null);
        if(cart == null){
            cart = createNewCart(userId);
        }

        Item item = itemService.getItem(cartAddDto.getItemId());
        CartItem cartItem = CartItem.builder().cart(cart).item(item).count(cartAddDto.getCount()).build();
        cart.addItem(cartItem);
    }

    private Cart createNewCart(Long userId ){
        User user = userService.findUserById(userId);
        return cartRepository.save(Cart.builder().user(user).build());
    }
}
