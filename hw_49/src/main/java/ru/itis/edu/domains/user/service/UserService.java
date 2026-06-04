package ru.itis.edu.domains.user.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.itis.edu.domains.user.dto.NewUserDto;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.entity.User;
import ru.itis.edu.domains.user.exceptions.UserNotFoundException;
import ru.itis.edu.domains.user.mapper.UserMapper;
import ru.itis.edu.domains.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto createUser(NewUserDto newUser) {

        User user = new User(newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getPassword(),
                newUser.getEmail(),
                newUser.getAge());

        user = userRepository.save(user);

        return userMapper.from(user);
    }

    public UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
        return userMapper.from(user);
    }
}
