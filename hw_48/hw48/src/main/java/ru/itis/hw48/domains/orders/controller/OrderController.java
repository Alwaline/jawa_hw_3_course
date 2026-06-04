package ru.itis.hw48.domains.orders.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.itis.hw48.domains.orders.dto.NewOrderDto;
import ru.itis.hw48.domains.orders.dto.OrderDto;
import ru.itis.hw48.domains.orders.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @PostMapping
    public OrderDto createOrder(@RequestBody @Valid NewOrderDto newOrder) {return orderService.createOrder(newOrder);}

    @GetMapping("/{order-id}")
    public OrderDto getOrder(@PathVariable("order-id") Long orderId) {
        return orderService.getOrderById(orderId);
    }
}
