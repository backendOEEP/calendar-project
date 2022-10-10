package com.example.study.calendarproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "schedule_id"),
        @Index(columnList = "user_id")
})
@Entity
public class Plan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false)
    private Schedule schedule;

    @Setter @ManyToOne(optional = false) @JoinColumn(name = "userId")
    private User user;

    protected Plan() {}

    private Plan(Schedule schedule, User user) {
        this.schedule = schedule;
        this.user = user;
    }

    public static Plan of(Schedule schedule, User user) {
        return new Plan(schedule, user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Plan){
            Plan plan = (Plan) o;
            return id != null && id.equals(plan.getId());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
