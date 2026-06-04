package ru.itis.shop.accounts.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.dto.NewAccountDto;

import java.util.List;

@RequestMapping("/api/v1/accounts")
public interface AccountApi {

    @Operation(summary = "Получение списка пользователей", description = "Тут описание")
    @ApiResponse(description = "Список всех пользователей", responseCode = "200")
    @GetMapping
    List<AccountDto> getAccounts();

    @Operation(summary = "Добавление пользователя", description = "Тут описание")
    @ApiResponse(description = "Информация о добавленном пользователе", responseCode = "201")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountDto addAccount(@RequestBody NewAccountDto newAccount);
}
