package ru.itis.shop.accounts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.shop.accounts.dto.AccountDto;
import ru.itis.shop.accounts.dto.NewAccountDto;
import ru.itis.shop.accounts.entity.Account;
import ru.itis.shop.accounts.repository.AccountRepository;

import java.util.List;

import static ru.itis.shop.accounts.dto.AccountDto.from;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountDto save(NewAccountDto newAccountDto) {
        Account newAccount = new Account();

        newAccount.setEmail(newAccountDto.getEmail());
        newAccount.setFirstName(newAccountDto.getFirstName());
        newAccount.setLastName(newAccountDto.getLastName());

        accountRepository.save(newAccount);

        return from(newAccount);
    }

    public List<AccountDto> getAccounts() {
        List<Account> accounts = accountRepository.findAll();

        return from(accounts);
    }
}
