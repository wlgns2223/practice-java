package org.example.commerce.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.commerce.constant.OrderState;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(nullable = false)
    private long totalAmount;

    @Column(nullable = false)
    private long discountAmount;

    @Column(nullable = false)
    private long finalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Orders(String orderNumber, OrderState orderState,  long totalAmount,long discountAmount, long finalAmount,
                  User user){
        this.orderNumber = orderNumber;
        this.orderState = orderState;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.user = user;
    }


}
