package ru.itis.shop.usecase.account.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.shop.domains.orders.dto.OrderDto;

import java.util.List;

@RequestMapping("/api/v1/")
public interface AccountOrdersApi {

    @Operation(summary = "Получение списка заказов пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Информация о заказах", responseCode = "200"),
            @ApiResponse(description = "Пользователь не найден", responseCode = "404")
    })
    @GetMapping("/accounts/{account-id}/orders")
    List<OrderDto> getAccountOrders(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("account-id") Long accountId);
}
