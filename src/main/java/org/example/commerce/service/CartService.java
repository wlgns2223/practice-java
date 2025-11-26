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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    final private CartRepository cartRepository;
    final private UserService userService;
    final private ItemService itemService;

    //TODO 동시성
    @Transactional
    public void addToCart(Long userId, CartAddDto cartAddDto){

        Cart cart = cartRepository.findById(userId).orElse(null);
        if(cart == null){
            cart = createNewCart(userId);
        }

        Optional<CartItem> cartItem = findItemInCart(cart, cartAddDto);

        if(cartItem.isPresent()){
            cartItem.get().updateCount(cartAddDto.getCount());
        } else {
            CartItem newItem = makeNewCartItem(cartAddDto);
            cart.addItem(newItem);
        }
    }

    private CartItem makeNewCartItem(CartAddDto cartAddDto){
        Item item = itemService.getItem(cartAddDto.getItemId());
        return CartItem.builder().item(item).count(cartAddDto.getCount()).build();
    }

    private Optional<CartItem> findItemInCart(Cart cart, CartAddDto cartAddDto){
        return cart.getItems()
                .stream()
                .filter(item -> item.getId().equals(cartAddDto.getItemId()))
                .findFirst();
    }

    private Cart createNewCart(Long userId ){
        User user = userService.findUserById(userId);
        return cartRepository.save(Cart.builder().user(user).build());
    }
}
