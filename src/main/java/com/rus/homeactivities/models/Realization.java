package com.rus.homeactivities.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "realizations")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Realization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Description should not be empty!")
    private String description;

    @NotEmpty(message = "Actual executor should not be empty!")
    @Column(name = "actual_executor")
    private String actualExecutor;

    @NotNull(message = "Actual completion date should not be NULL and " +
            "should be like this format 'DD-MM-YYYY'!")
    @Column(name = "actual_completion_date")
    private LocalDate actualCompletionDate;

    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "change_date")
    private LocalDateTime changeDate;

    @ManyToOne
    @JoinColumn(name = "id_activity")
    private Activity activity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Realization that = (Realization) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
