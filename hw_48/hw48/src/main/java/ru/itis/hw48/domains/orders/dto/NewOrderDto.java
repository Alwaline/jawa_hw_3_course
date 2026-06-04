package ru.itis.hw48.domains.orders.dto;

import jakarta.validation.constraints.NotNull;
import ru.itis.hw48.domains.user.dto.UserDto;

import java.time.LocalDateTime;

public class NewOrderDto {

    @NotNull(message = "Order must having owner")
    private UserDto owner;
    @NotNull(message = "Order must have orderDate")
    private LocalDateTime orderDate;

    public NewOrderDto() {
    }
    public NewOrderDto(UserDto owner, LocalDateTime orderDate) {
        this.owner = owner;
        this.orderDate = orderDate;
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
