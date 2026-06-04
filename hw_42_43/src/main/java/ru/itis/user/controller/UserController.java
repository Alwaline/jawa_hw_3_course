package ru.itis.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.user.dto.NewUserDto;
import ru.itis.user.dto.UserDto;
import ru.itis.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return  userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@RequestBody NewUserDto dto, @PathVariable("id") Long id) {
        return userService.update(dto, id);
    }

    @PostMapping
    public UserDto save(@RequestBody NewUserDto dto) {
        return userService.create(dto);
    }
}
