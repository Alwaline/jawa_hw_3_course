package ru.itis.hw48.domains.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hw48.domains.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
