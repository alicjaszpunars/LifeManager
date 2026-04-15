package pl.coderslab.lifemanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "habit_tracker")
public class HabitTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private Boolean done;

    @ManyToOne(optional = false)
    @JoinColumn(name = "daily_entry_id")
    private DailyEntry dailyEntry;

}

