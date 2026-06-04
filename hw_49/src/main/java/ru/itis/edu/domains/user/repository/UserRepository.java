package ru.itis.edu.domains.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.edu.domains.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
