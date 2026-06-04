package ru.itis.edu.domains.user.dto;

import jakarta.validation.constraints.*;

public class NewUserDto {

    @NotBlank(message = "EDU: Null value/empty value are not allowed")
    private String firstName;

    @NotBlank(message = "EDU: Null value/empty value are not allowed")
    private String lastName;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])[A-Za-z0-9]+$", message = "Not safe password")
    private String password;

    @Email(message = "Invalid format of email")
    private String email;

    @Min(value = 0, message = "age is lower than 0")
    @Max(value = 120, message = "age is upper than 120")
    private Integer age;

    public NewUserDto() {
    }

    public NewUserDto(String firstName, String lastName, String password, String email, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
