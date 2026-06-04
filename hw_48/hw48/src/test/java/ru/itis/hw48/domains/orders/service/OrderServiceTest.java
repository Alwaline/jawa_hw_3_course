package ru.itis.hw48.domains.orders.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.hw48.domains.orders.dto.NewOrderDto;
import ru.itis.hw48.domains.orders.dto.OrderDto;
import ru.itis.hw48.domains.orders.entity.Order;
import ru.itis.hw48.domains.orders.exceptions.OrderNotFoundException;
import ru.itis.hw48.domains.orders.mapper.OrderMapper;
import ru.itis.hw48.domains.orders.repository.OrderRepository;
import ru.itis.hw48.domains.user.dto.UserDto;
import ru.itis.hw48.domains.user.entity.User;
import ru.itis.hw48.domains.user.exceptions.UserNotFoundException;
import ru.itis.hw48.domains.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderMapper orderMapper;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    OrderService orderService;

    @Nested
    @DisplayName("createOrder() works ...")
    class CreateOrder {

        @Test
        public void create_order_returns_created_order_with_id() {
            UserDto ownerDto = new UserDto(1L, "Andrew", "Ganiev", "agan@mail.com", 26);
            LocalDateTime orderDate = LocalDateTime.of(2026, 6, 1, 10, 0);

            NewOrderDto newOrderDto = new NewOrderDto(ownerDto, orderDate);

            User user = new User(1L, "Andrew", "Ganiev", "1234", "agan@mail.com", 26);
            Order savedOrder = new Order(1L, user, orderDate);
            OrderDto expectedOrder = new OrderDto(1L, ownerDto, orderDate);

            when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);
            when(orderMapper.from(any(Order.class))).thenReturn(expectedOrder);

            OrderDto createdOrder = orderService.createOrder(newOrderDto);

            verify(userRepository).findById(1L);
            verify(orderRepository).save(any(Order.class));
            verify(orderMapper).from(any(Order.class));

            assertEquals(expectedOrder, createdOrder);
        }

        @Test
        public void create_order_throws_when_user_not_found() {
            UserDto ownerDto = new UserDto(99L, "Andrew", "Ganiev", "agan@mail.com", 26);
            NewOrderDto newOrderDto = new NewOrderDto(ownerDto, LocalDateTime.now());

            when(userRepository.findById(99L)).thenReturn(Optional.empty());

            assertThrows(UserNotFoundException.class, () -> orderService.createOrder(newOrderDto));
        }
    }

    @Nested
    @DisplayName("getOrderById() works ...")
    class GetOrderById {

        @Test
        public void get_order_returns_order_with_id() {
            LocalDateTime orderDate = LocalDateTime.of(2026, 6, 1, 10, 0);
            UserDto ownerDto = new UserDto(1L, "Andrew", "Ganiev", "agan@mail.com", 26);
            User user = new User(1L, "Andrew", "Ganiev", "1234", "agan@mail.com", 26);

            Order orderFromRepository = new Order(1L, user, orderDate);
            OrderDto expectedOrder = new OrderDto(1L, ownerDto, orderDate);

            when(orderRepository.findById(1L)).thenReturn(Optional.of(orderFromRepository));
            when(orderMapper.from(any(Order.class))).thenReturn(expectedOrder);

            OrderDto actualOrder = orderService.getOrderById(1L);

            assertEquals(expectedOrder, actualOrder);
        }

        @Test
        public void get_order_throws_when_order_not_found() {
            when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

            assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(1L));
        }
    }
}
