package org.example.commerce.constant;

public enum OrderState {
    PENDING_PAYMENT("결제 대기"),

    PAYMENT_COMPLETED("결제 완료"),

    CANCELLED("주문 취소");

    private OrderState(final String description){}
}
