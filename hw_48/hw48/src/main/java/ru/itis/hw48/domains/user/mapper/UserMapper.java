package ru.itis.hw48.domains.user.mapper;

import org.springframework.stereotype.Component;
import ru.itis.hw48.domains.user.entity.User;

@Component
public class UserMapper {

    public ru.itis.hw48.domains.user.dto.UserDto from(User user) {
        return new ru.itis.hw48.domains.user.dto.UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge());
    }
}
