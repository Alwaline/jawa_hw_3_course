package ru.itis.hw48.domains.orders.mapper;

import org.springframework.stereotype.Component;
import ru.itis.hw48.domains.orders.dto.OrderDto;
import ru.itis.hw48.domains.orders.entity.Order;
import ru.itis.hw48.domains.user.mapper.UserMapper;

@Component
public class OrderMapper {

    private final UserMapper userMapper;

    public OrderMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public OrderDto from(Order order) {
        return new OrderDto(
                order.getId(),
                userMapper.from(order.getOwner()),
                order.getOrderDate()
        );
    }
}
