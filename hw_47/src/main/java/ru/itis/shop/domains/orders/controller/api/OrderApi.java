package ru.itis.shop.domains.orders.controller.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.shop.domains.orders.dto.NewOrderDto;
import ru.itis.shop.domains.orders.dto.OrderDto;

@RequestMapping("/api/v1/orders")
public interface OrderApi {

    @Operation(summary = "Добавление заказа", description = "Тут описание")
    @ApiResponse(description = "Информация о добавленном заказе", responseCode = "201")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    OrderDto addOrder(@RequestBody NewOrderDto newOrder);
}
