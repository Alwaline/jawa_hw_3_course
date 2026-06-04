package ru.itis.hw48.domains.user.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.hw48.domains.user.dto.NewUserDto;
import ru.itis.hw48.domains.user.dto.UserDto;
import ru.itis.hw48.domains.user.entity.User;
import ru.itis.hw48.domains.user.exceptions.UserNotFoundException;
import ru.itis.hw48.domains.user.mapper.UserMapper;
import ru.itis.hw48.domains.user.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Nested
    @DisplayName("createUser() works ...")
    class CreateUser {
        @Test
        public void create_user_returns_created_user_with_id() {
            NewUserDto newUserDto = new NewUserDto(
                "Andrew",
                "Ganiev",
                "1234",
                "agan@mail.com",
                26);

            User savedUser = new User(
                1L,
                "Andrew",
                "Ganiev",
                "1234",
                "agan@mail.com",
                26);

            UserDto expectedUser =  new UserDto(
                1L,
                "Andrew",
                "Ganiev",
                "agan@mail.com",
                26
            );

            when(userRepository.save(any(User.class))).thenReturn(savedUser);
            when(userMapper.from(any(User.class))).thenReturn(expectedUser);

            UserDto createdUser = userService.createUser(newUserDto);

            verify(userRepository).save(any(User.class));
            verify(userMapper).from(any(User.class));

            assertEquals(expectedUser, createdUser);
        }
    }

    @Nested
    @DisplayName("getUser() works ...")
    class GetUser {
        @Test
        public void get_user_returns_user_with_id() {
            User userFromRepository = new User(
                1L,
                "Andrew",
                "Ganiev",
                "1234",
                "agan@mail.com",
                26);

            UserDto expectedUser = new UserDto(
                1L,
                "Andrew",
                "Ganiev",
                "agan@mail.com",
                26);

            when(userRepository.findById(1L)).thenReturn(Optional.of(userFromRepository));
            when(userMapper.from(any(User.class))).thenReturn(expectedUser);

            UserDto actualUser = userService.getUser(1L);

            assertEquals(expectedUser, actualUser);
        }

        @Test
        public void get_user_returns_throws_when_user_not_found() {
            when(userRepository.findById(anyLong())).thenThrow(UserNotFoundException.class);
            assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
        }
    }
}
