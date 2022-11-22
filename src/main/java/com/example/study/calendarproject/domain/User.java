package com.example.study.calendarproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(name = "users", indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "nickname", unique = true)
})
@Entity
public class User {
    @Id @Column(length=30) private String userId;

    @Setter @Column(nullable = false) private String password;
    @Setter @Column(length = 50) private String userName;
    @Setter private String email;

    @ToString.Exclude @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<Plan> userPlans = new LinkedHashSet<>();

    protected User() {}

    private User(String userId, String password, String userName, String email) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
    }

    public static User of(String userId, String userPassword, String userName, String email) {
        return new User(userId, userPassword, userName, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof User){
            User user = (User) o;
            return userId != null && userId.equals(user.getUserId());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

}
