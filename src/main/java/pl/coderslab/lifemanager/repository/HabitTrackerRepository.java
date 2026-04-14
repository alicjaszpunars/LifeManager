package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Habit;
import pl.coderslab.lifemanager.entity.HabitTracker;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitTrackerRepository extends JpaRepository<HabitTracker, Long> {

    Optional<HabitTracker> findByHabitAndDailyEntry(
            Habit habit,
            DailyEntry dailyEntry
    );

    List<HabitTracker> findAllByDailyEntry(DailyEntry dailyEntry);

    List<HabitTracker> findAllByHabitAndDailyEntry_DateBetween(Habit habit, LocalDate startDate, LocalDate endDate);
}
