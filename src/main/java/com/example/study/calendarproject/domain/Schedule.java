package com.example.study.calendarproject.domain;

import com.example.study.calendarproject.domain.constant.Category;
import com.example.study.calendarproject.domain.constant.Repeat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "category"),
        @Index(columnList = "date"),
        @Index(columnList = "start_time")
})
@Entity
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Not Null 필드
    @Setter @Column(nullable = false) private String name; //이름
    @Setter @Column(nullable = false) private String location; //장소
    @Setter @Enumerated(EnumType.STRING) @Column(nullable = false) private Category category; //카테고리
    @Setter @Column(nullable = false) private LocalDateTime start_time; //시작 시간
    @Setter @Column(nullable = false) private LocalDateTime end_time; //종료 시간

    //Nullable 필드
    @Setter private String description; //설명
    @Setter @Enumerated(EnumType.STRING) private Repeat repeat; //반복 옵션

    @ToString.Exclude @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private final Set<Plan> schedulePlans = new LinkedHashSet<>();

    protected Schedule() {}

    private Schedule(String name, String location, Category category, LocalDateTime start_time, LocalDateTime end_time, String description, Repeat repeat) {
        this.name = name;
        this.location = location;
        this.category = category;
        this.start_time = start_time;
        this.end_time = end_time;
        this.description = description;
        this.repeat = repeat;
    }

    public static Schedule of(String name, String location, Category category, LocalDateTime start_time, LocalDateTime end_time, String description, Repeat repeat){
        return new Schedule(name, location, category, start_time, end_time, description, repeat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // 11은 instanceof 패턴 매칭을 지원하지 않는대요..ㅠㅠㅠ
        if (o instanceof Schedule){
            Schedule schedule = (Schedule) o;
            return id != null && id.equals(schedule.getId());
        }
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
