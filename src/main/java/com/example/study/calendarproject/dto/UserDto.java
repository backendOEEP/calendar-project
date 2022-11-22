package com.example.study.calendarproject.dto;

import com.example.study.calendarproject.domain.User;
import lombok.Getter;

@Getter
public class UserDto {
    String userId;
    String password;
    String userName;
    String email;

    protected UserDto() {}

    private UserDto(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public static UserDto of(String userId, String password, String userName, String email) {
        return new UserDto(userId, password, userName, email);
    }

    public static UserDto from(User entity) {
        return new UserDto(
                entity.getUserId(),
                entity.getPassword(),
                entity.getUserName(),
                entity.getEmail()
        );
    }

    public User toEntity() {
        return User.of(userId, password, userName, email);
    }

}
