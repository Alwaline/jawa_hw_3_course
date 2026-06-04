package ru.itis.shop.accounts.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.dto.NewAccountDto;
import ru.itis.shop.dto.PageDto;

@RequestMapping("/api/v1/accounts")
public interface AccountApi {

    @Operation(summary = "Получение списка пользователей", description = "Тут описание")
    @ApiResponse(description = "Страница с пользователями", responseCode = "200")
    @GetMapping
    PageDto<AccountDto> getAccounts(
            @Parameter(description = "Номер страницы с пользователями") @RequestParam("page") int page,
            @Parameter(description = "Размер страницы с пользователями") @RequestParam("size") int size,
            @Parameter(description = "Сортировка страницы с пользователями") @RequestParam("sort") String sort);

    @Operation(summary = "Добавление пользователя", description = "Тут описание")
    @ApiResponse(description = "Информация о добавленном пользователе", responseCode = "201")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountDto addAccount(@RequestBody NewAccountDto newAccount);

    @Operation(summary = "Получение отдельного пользователя")
    @ApiResponses(value = {
            @ApiResponse(description = "Информация о пользователе", responseCode = "200"),
            @ApiResponse(description = "Пользователь не найден", responseCode = "404")
    })
    @GetMapping("/{account-id}")
    AccountDto getAccount(
            @Parameter(description = "Идентификатор пользователя") @PathVariable("account-id") Long accountId);
}
