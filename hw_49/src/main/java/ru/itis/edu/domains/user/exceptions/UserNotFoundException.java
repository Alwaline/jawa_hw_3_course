package ru.itis.edu.domains.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.edu.handler.exceptions.NotFoundException;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(Long id) {
        super("User with id <" + id + "> not found.");
    }
}
