package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.HabitTracker;

import java.time.LocalDate;
import java.util.List;

public interface HabitTrackerRepository extends JpaRepository <HabitTracker, Long>  {
    List<HabitTracker> findAllByDailyEntry(DailyEntry dailyEntry);
    List<HabitTracker> findAllByDailyEntry_DateBetween(LocalDate start, LocalDate end);
}
