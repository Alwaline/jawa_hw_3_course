package ru.itis.hw48.domains.orders.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hw48.domains.orders.dto.OrderDto;
import ru.itis.hw48.domains.orders.exceptions.OrderNotFoundException;
import ru.itis.hw48.domains.orders.service.OrderService;
import ru.itis.hw48.domains.user.dto.UserDto;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    OrderService orderService;

    @Nested
    class GetOrder {

        @Test
        public void getOrderById() throws Exception {
            UserDto ownerDto = new UserDto(1L, "Andrew", "Ganiev", "agan@mail.com", 26);
            OrderDto expectedOrder = new OrderDto(1L, ownerDto, LocalDateTime.of(2026, 6, 1, 10, 0));

            when(orderService.getOrderById(1L)).thenReturn(expectedOrder);

            mockMvc.perform(get("/api/v1/orders/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.owner.email").value("agan@mail.com"));
        }

        @Test
        public void getOrderByIdNotFound() throws Exception {
            when(orderService.getOrderById(anyLong())).thenThrow(new OrderNotFoundException(1L));

            mockMvc.perform(get("/api/v1/orders/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class CreateOrder {

        @Test
        public void createOrder() throws Exception {
            UserDto ownerDto = new UserDto(1L, "Andrew", "Ganiev", "agan@mail.com", 26);
            OrderDto expectedOrder = new OrderDto(1L, ownerDto, LocalDateTime.of(2026, 6, 1, 10, 0));

            when(orderService.createOrder(any())).thenReturn(expectedOrder);

            mockMvc.perform(post("/api/v1/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"owner\": {\"id\": 1, \"firstName\": \"Andrew\", \"lastName\": \"Ganiev\", \"email\": \"agan@mail.com\", \"age\": 26}," +
                                    "\"orderDate\": \"2026-06-01T10:00:00\"}"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.owner.email").value("agan@mail.com"));
        }

        @Test
        public void create_validation_error() throws Exception {
            mockMvc.perform(post("/api/v1/orders")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{}"))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value("Validation error"))
                    .andExpect(jsonPath("$.errors.owner").value("Order must having owner"))
                    .andExpect(jsonPath("$.errors.orderDate").value("Order must have orderDate"));
        }
    }
}
