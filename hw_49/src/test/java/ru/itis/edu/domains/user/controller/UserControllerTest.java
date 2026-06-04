package ru.itis.edu.domains.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.edu.domains.user.dto.UserDto;
import ru.itis.edu.domains.user.exceptions.UserNotFoundException;
import ru.itis.edu.domains.user.service.UserService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Test
    public void get_user_returns_correct_user() throws Exception {
        UserDto expectedUser = new UserDto(
                1L, "Marsel", "Sidikov", "qwerty007", "marsel@mail.com", 32);

        when(userService.getUser(1L)).thenReturn(expectedUser);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.password").value("qwerty007"));
    }

    @Test
    public void get_user_returns_not_found_when_wrong_id() throws Exception {

        when(userService.getUser(anyLong())).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isNotFound());
    }

}