package tech.fiap.project.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @org.junit.jupiter.api.Test
    void test() {
        assertEquals(OrderStatus.PENDING, OrderStatus.valueOf("PENDING"));
        assertEquals(OrderStatus.AWAITING_PAYMENT, OrderStatus.valueOf("AWAITING_PAYMENT"));
        assertEquals(OrderStatus.PAID, OrderStatus.valueOf("PAID"));
        assertEquals(OrderStatus.CANCELED, OrderStatus.valueOf("CANCELED"));
        assertEquals(OrderStatus.FINISHED, OrderStatus.valueOf("FINISHED"));
    }

}