package ru.itis.hw48.domains.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hw48.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
