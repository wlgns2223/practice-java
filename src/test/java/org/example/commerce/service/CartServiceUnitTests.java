package org.example.commerce.service;

import org.example.commerce.constant.Role;
import org.example.commerce.dto.CartAddDto;
import org.example.commerce.entity.*;
import org.example.commerce.repository.CartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.CheckedOutputStream;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Cart Test suites")
@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTests {

    private static final Logger log = LoggerFactory.getLogger(CartServiceUnitTests.class);
    @Mock
    private CartRepository cartRepository;



    @Mock
    private  ItemService itemService;

    @InjectMocks
    private CartService sut;


    @Test
    @DisplayName("장바구니에 아이템을 추가할 수 있다.")
    public void add_item_to_cart(){
        Long userId = 1L;
        CartAddDto cartAddDto = CartAddDto.builder().itemId(1L).count(5).build();
        User userStub = User.builder()
                .username("foo")
                .password("password")
                .email("email@gmail.com")
                .role(Role.USER)
                .build();

        Cart cartStub = Cart.of(userStub);
        Item itemStub = Car.builder()
                .name("item")
                .price(1000)
                .stockQuantity(10)
                .brand("brand")
                .type("SUV")
                .build();

        CartItem cartItemStub = CartItem.of(itemStub, cartAddDto.getCount());

        when(itemService.getItem(1L)).thenReturn(itemStub);
        when(cartRepository.findById(userId)).thenReturn(Optional.of(cartStub));


        Cart actual = sut.addToCart(userId, cartAddDto);

        assertThat(actual.getItems()).contains(cartItemStub);
    }

    @Test
    @DisplayName("재고보다 많은 상품을 주문할 수 없다.")
    public void cannot_order_more_than_stock(){
        Long userId = 1L;
        CartAddDto cartAddDto = CartAddDto.builder().itemId(1L).count(10).build();
        User userStub = User.builder()
                .username("foo")
                .password("password")
                .email("email@gmail.com")
                .role(Role.USER)
                .build();

        Cart cartStub = Cart.of(userStub);
        Item itemStub = Car.builder()
                .name("item")
                .price(1000)
                .stockQuantity(1)
                .brand("brand")
                .type("SUV")
                .build();


        when(itemService.getItem(1L)).thenReturn(itemStub);
        when(cartRepository.findById(userId)).thenReturn(Optional.of(cartStub));

        assertThatThrownBy(() -> sut.addToCart(userId, cartAddDto))
                .isInstanceOf(RuntimeException.class);

    }

}
