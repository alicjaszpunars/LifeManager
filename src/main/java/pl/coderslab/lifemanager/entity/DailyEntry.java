package pl.coderslab.lifemanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "daily_entry")
public class DailyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PastOrPresent (message= "data cannot be in the future")
    @Column(nullable = false, unique = true)
    private LocalDate date = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private Mood mood;

    private String diary;


}
