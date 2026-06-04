package ru.itis.shop.domains.orders.dto;

import ru.itis.shop.domains.orders.entity.Order;

import java.util.List;

public class OrderDto {

    private Long orderId;
    private Long accountId;
    private String date;

    public OrderDto(Long orderId, Long accountId, String date) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.date = date;
    }

    public static OrderDto from(Order order) {
        return new OrderDto(order.getId(), order.getOwner().getId(), order.getDate().toString());
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).toList();
    }

    public OrderDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
