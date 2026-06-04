package ru.itis.shop.domains.accounts.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.shop.domains.accounts.controller.api.AccountApi;
import ru.itis.shop.domains.accounts.dto.AccountDto;
import ru.itis.shop.domains.accounts.dto.NewAccountDto;
import ru.itis.shop.domains.orders.dto.OrderDto;
import ru.itis.shop.dto.PageDto;
import ru.itis.shop.domains.accounts.service.AccountService;

import java.util.List;

@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public PageDto<AccountDto> getAccounts(int page, int size, String sort) {
        return accountService.getAccounts(page, size, sort);
    }

    @Override
    public AccountDto addAccount(NewAccountDto newAccount) {
        return accountService.save(newAccount);
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        return accountService.getAccount(accountId);
    }
}
