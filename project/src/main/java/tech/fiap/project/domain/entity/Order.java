package tech.fiap.project.domain.entity;


import tech.fiap.project.app.dto.OrderStatus;
import tech.fiap.project.app.dto.PaymentDTO;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

    public Order(Long id, OrderStatus status, LocalDateTime createdDate, LocalDateTime updatedDate, List<Item> items, Payment payment) {
        this.id = id;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.items = items;
        this.payment = payment;
    }

    private Long id;
    private OrderStatus status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private List<Item> items;

    private Payment payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}