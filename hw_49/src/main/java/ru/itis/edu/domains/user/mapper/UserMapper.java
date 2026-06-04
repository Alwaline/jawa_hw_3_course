package ru.itis.edu.domains.user.mapper;

import org.springframework.stereotype.Component;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.entity.User;

@Component
public class UserMapper {

    public UserDto from(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getAge());
    }
}
