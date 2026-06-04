package ru.itis.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
