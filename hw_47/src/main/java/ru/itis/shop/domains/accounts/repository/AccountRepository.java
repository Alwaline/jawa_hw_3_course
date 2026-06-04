package ru.itis.shop.domains.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.domains.accounts.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsAccountById(Long id);
}
