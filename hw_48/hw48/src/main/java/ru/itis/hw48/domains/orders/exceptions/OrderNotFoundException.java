package ru.itis.hw48.domains.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.hw48.handler.exceptions.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(Long orderId) {
        super("Order with id " + orderId + " not found");
    }
}
