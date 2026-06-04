package ru.itis.shop.domains.orders.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.shop.domains.orders.controller.api.OrderApi;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;
import ru.itis.shop.domains.orders.service.OrderService;

@RestController
public class OrderController implements OrderApi {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public OrderDto addOrder(NewOrderDto newOrder) {
        return orderService.addOrder(newOrder);
    }
}
