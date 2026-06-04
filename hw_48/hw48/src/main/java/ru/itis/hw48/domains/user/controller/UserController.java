package ru.itis.hw48.domains.user.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.hw48.domains.user.dto.NewUserDto;
import ru.itis.hw48.domains.user.dto.UserDto;
import ru.itis.hw48.domains.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid NewUserDto newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/{user-id}")
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return userService.getUser(userId);
    }
}
