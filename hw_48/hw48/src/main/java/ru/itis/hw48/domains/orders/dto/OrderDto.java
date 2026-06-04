package ru.itis.hw48.domains.orders.dto;

import ru.itis.hw48.domains.user.dto.UserDto;

import java.time.LocalDateTime;

public class OrderDto {

    private Long id;
    private UserDto owner;
    private LocalDateTime orderDate;

    public OrderDto(Long id, UserDto owner, LocalDateTime orderDate) {
        this.id = id;
        this.owner = owner;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
