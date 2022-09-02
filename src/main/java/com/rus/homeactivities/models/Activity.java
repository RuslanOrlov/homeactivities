package com.rus.homeactivities.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "activities")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Activity should not be empty!")
    @Column(name = "activity")
    private String activityName;

    @NotEmpty(message = "Executor should not be empty!")
    @Column(name = "executor")
    private String executorName;

    @NotNull(message = "Completion date should not be NULL and " +
                        "should be like this format 'DD-MM-YYYY'!")
    @Column(name = "completion_date")
    private LocalDate completionDate;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    @ToString.Exclude
    private List<Realization> realizationList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Activity activity = (Activity) o;
        return id != null && Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
