package ru.itis.shop.accounts.controller;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.shop.accounts.controller.api.AccountApi;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.dto.NewAccountDto;
import ru.itis.shop.accounts.service.AccountService;

import java.util.List;

@RestController
public class AccountController implements AccountApi {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public List<AccountDto> getAccounts() {
        return accountService.getAccounts();
    }

    public AccountDto addAccount(NewAccountDto newAccount) {
        return accountService.save(newAccount);
    }
}
