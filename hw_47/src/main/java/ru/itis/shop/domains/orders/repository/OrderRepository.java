package ru.itis.shop.domains.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.shop.domains.orders.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOwner_Id(Long ownerId);
}
