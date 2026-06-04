package ru.itis.edu.domains.user.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itis.edu.domains.user.dto.NewUserDto;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.entity.User;
import ru.itis.edu.domains.user.exceptions.UserNotFoundException;
import ru.itis.edu.domains.user.mapper.UserMapper;
import ru.itis.edu.domains.user.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UserServiceTest {

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
            // новый пользователь, который идет в метод createUser
            NewUserDto newUser = new NewUserDto("Marsel", "Sidikov", "qwerty007", "marsel@mail.com", 32);

            // Объект, который мы ожидаем получить после работы репозитория
            User savedUser = new User(1L, "Marsel",
                    "Sidikov",
                    "qwerty007",
                    "marsel@mail.com",
                    32);

            // Объект, который мы получим после работы метода createUser
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "qwerty007", "marsel@mail.com", 32);

            // stubbing - мы пишем, какие входные и выходные данные у методов без их конкретной реализации, потому что мы тестируем только сервис

            // когда у репозитория вызывают save с любым пользователем - мы возвращаем сохраненный объект, который описали ранее
            when(userRepository.save(any(User.class))).thenReturn(savedUser);
            // когда у маппера просят сконвертировать любой объект в DTO - мы возвращаем заранее подготовленный объект
            when(userMapper.from(any(User.class))).thenReturn(expectedUser);

            UserDto actualUser = userService.createUser(newUser);

            verify(userRepository).save(any(User.class));
            verify(userMapper).from(any(User.class));

            assertEquals(expectedUser, actualUser);
        }
    }

    @Nested
    @DisplayName("getUser() works ...")
    class GetUser {

        @Test
        public void get_user_returns_correct_user() {
            // подготовка данных

            // ожидаемый пользователь из репозитория
            User userFromRepository = new User(1L, "Marsel",
                    "Sidikov",
                    "qwerty007",
                    "marsel@mail.com",
                    32);

            // ожидаемый пользователь из сервиса
            UserDto expectedUser = new UserDto(
                    1L, "Marsel", "Sidikov", "qwerty007", "marsel@mail.com", 32);

            // когда репозиторию посылают 1L, он должен вернуть заранее подготовленный объект
            when(userRepository.findById(1L)).thenReturn(Optional.of(userFromRepository));
            // когда просим сконвертировать объект - возвращаем ожидаемого пользователя
            when(userMapper.from(any(User.class))).thenReturn(expectedUser);

            UserDto actualUser = userService.getUser(1L);

            assertEquals(expectedUser, actualUser);
        }

        @Test
        public void get_user_throws_exception_when_wrong_user_id() {
            when(userRepository.findById(anyLong())).thenThrow(UserNotFoundException.class);

            assertThrows(UserNotFoundException.class, () -> userService.getUser(1L));
        }
    }
}