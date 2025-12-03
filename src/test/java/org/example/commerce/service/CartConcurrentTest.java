package org.example.commerce.service;

import com.zaxxer.hikari.HikariDataSource;
import org.example.commerce.entity.Car;
import org.example.commerce.entity.Item;
import org.example.commerce.repository.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


@SpringBootTest
public class CartConcurrentTest {


    private Item sharedItem;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    private static int threadNum = 32;


    @BeforeEach
    void setUp() {
        sharedItem = Car.builder()
                .name("car")
                .price(1000)
                .stockQuantity(10)
                .brand("brand")
                .type("SUV")
                .build();
        itemRepository.save(sharedItem);
    }

    @AfterEach
    void tearDown() {
//        itemRepository.deleteAll();
    }

    @Test
    @DisplayName("동시에 32명이 동시에 재고 1개씩 차감한다.")
    void concurrent_stock_decrease() throws InterruptedException {
        Long itemId = sharedItem.getId();

        ExecutorService executor = Executors.newFixedThreadPool(threadNum);
        CountDownLatch readyLatch = new CountDownLatch(threadNum);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadNum);

        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        for (int i = 0; i < threadNum; i++) {
            executor.submit(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();

//                    itemService.decreaseStock(itemId,1);
                    Item item = itemRepository.findById(itemId).orElseThrow();
                    item.decreaseStock(1);
                    itemRepository.save(item);

                    success.incrementAndGet();


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    fail.incrementAndGet();

                } finally {
                    doneLatch.countDown();
                }
            });
        }

        readyLatch.await();
        startLatch.countDown();
        doneLatch.await();
        executor.shutdown();

        Item item = itemRepository.findById(itemId).orElseThrow();
        System.out.println("=== 결과 ===");
        System.out.println("성공: " + success.get());
        System.out.println("실패: " + fail.get());
        System.out.println("초기 재고: 10");
        System.out.println("실제 재고: " + item.getStockQuantity());
    }
}
