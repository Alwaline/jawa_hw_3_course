package ru.itis.hw48.domains.user.controller;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hw48.domains.user.dto.NewUserDto;
import ru.itis.hw48.domains.user.dto.UserDto;
import ru.itis.hw48.domains.user.exceptions.UserNotFoundException;
import ru.itis.hw48.domains.user.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    UserService userService;

    @Nested
    class GetUser {
        @Test
        public void getUserById() throws Exception {
            UserDto expectedUser = new UserDto(
                1L,
                "Andrew",
                "Ganiev",
                "agan@mail.com",
                26);

            when(userService.getUser(1L)).thenReturn(expectedUser);

            mockMvc.perform(get("/api/v1/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.firstName").value("Andrew"));
        }

        @Test
        public void getUserByIdNotFound() throws Exception {
            when(userService.getUser(anyLong())).thenThrow(UserNotFoundException.class);

            mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isNotFound());

        }
    }

    @Nested
    class CreateUser {
        @Test
        public void createUser() throws Exception {
            NewUserDto postUser = new NewUserDto();

            UserDto expectedUser = new UserDto(
                1L,
                "Andrew",
                "Ganiev",
                "agan@mail.com",
                26);

            when(userService.createUser(any(NewUserDto.class))).thenReturn(expectedUser);

            mockMvc.perform(post("/api/v1/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Andrew\",\n" +
                            "  \"lastName\": \"Ganiev\",\n" +
                            "  \"password\": \"Aa1234\",\n" +
                            "  \"email\": \"agan@mail.com\",\n" +
                            "  \"age\": 26}"))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.email").value("agan@mail.com"));
        }

        @Test
        public void create_validation_error() throws Exception {
            mockMvc.perform(post("/api/v1/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"firstName\": \"\",\n" +
                                    "  \"lastName\": \"\",\n" +
                                    "  \"password\": \"1234\",\n" +
                                    "  \"email\": \"aga.com\",\n" +
                                    "  \"age\": 155}"))
                    .andExpect(status().is4xxClientError())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.message").value("Validation error"))
                    .andExpect(jsonPath("$.errors.firstName").value("hw48: Null value/empty value are not allowed"))
                    .andExpect(jsonPath("$.errors.password").value("Not safe password"))
                    .andExpect(jsonPath("$.errors.email").value("Invalid format of email"))
                    .andExpect(jsonPath("$.errors.age").value("age is upper than 120"));
        }
    }




}
