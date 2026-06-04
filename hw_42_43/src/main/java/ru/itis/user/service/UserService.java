package ru.itis.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.user.dto.NewUserDto;
import ru.itis.user.dto.UserDto;
import ru.itis.user.entity.User;
import ru.itis.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDto create(NewUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());

        User saved =  userRepository.save(user);
        return new UserDto(saved.getId().toString(), saved.getName(), saved.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(new UserDto(user.getId().toString(), user.getName(), user.getEmail()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public UserDto getById(Long id) {
        User findUser = userRepository.findById(id).get();

        return new UserDto(findUser.getId().toString(), findUser.getName(), findUser.getEmail());
    }

    private User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public UserDto update(NewUserDto dto, Long id) {
        User user = getUser(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        return new UserDto(user.getId().toString(), user.getName(), user.getEmail());
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
