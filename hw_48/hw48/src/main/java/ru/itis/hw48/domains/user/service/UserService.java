package ru.itis.hw48.domains.user.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.itis.hw48.domains.user.dto.NewUserDto;
import ru.itis.hw48.domains.user.entity.User;
import ru.itis.hw48.domains.user.exceptions.UserNotFoundException;
import ru.itis.hw48.domains.user.mapper.UserMapper;
import ru.itis.hw48.domains.user.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public ru.itis.hw48.domains.user.dto.UserDto createUser(NewUserDto newUser) {

        User user = new User(newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getPassword(),
                newUser.getEmail(),
                newUser.getAge());

        userRepository.save(user);

        return userMapper.from(user);
    }

    public ru.itis.hw48.domains.user.dto.UserDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
        return userMapper.from(user);
    }
}
