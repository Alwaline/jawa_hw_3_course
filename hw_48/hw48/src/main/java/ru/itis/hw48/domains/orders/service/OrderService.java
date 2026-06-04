package ru.itis.hw48.domains.orders.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.hw48.domains.orders.dto.NewOrderDto;
import ru.itis.hw48.domains.orders.dto.OrderDto;
import ru.itis.hw48.domains.orders.entity.Order;
import ru.itis.hw48.domains.orders.exceptions.OrderNotFoundException;
import ru.itis.hw48.domains.orders.mapper.OrderMapper;
import ru.itis.hw48.domains.orders.repository.OrderRepository;
import ru.itis.hw48.domains.user.entity.User;
import ru.itis.hw48.domains.user.exceptions.UserNotFoundException;
import ru.itis.hw48.domains.user.repository.UserRepository;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public OrderDto createOrder(NewOrderDto newOrder) {
        Optional<User> user = userRepository.findById(newOrder.getOwner().getId());
        if (user.isEmpty()) {
            throw  new UserNotFoundException(newOrder.getOwner().getId());
        }
        Order order = new Order(
                user.get(),
                newOrder.getOrderDate()
        );

        orderRepository.save(order);

        return orderMapper.from(order);
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        return orderMapper.from(order);
    }
}
