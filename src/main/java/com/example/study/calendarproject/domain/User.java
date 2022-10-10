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
@Table(indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "nickname", unique = true)
})
@Entity
public class User {
    @Id @Column(length=30) private String userId;

    @Setter @Column(nullable = false) private String password;
    @Setter @Column(length = 50) private String nickname;
    @Setter private String email;

    @ToString.Exclude @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final Set<Plan> userPlans = new LinkedHashSet<>();

    protected User() {}

    private User(String userId, String password, String nickname, String email) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public static User of(String userId, String userPassword, String nickname, String email) {
        return new User(userId, userPassword, nickname, email);
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
