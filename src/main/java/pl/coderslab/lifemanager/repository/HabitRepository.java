package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.Habit;

import java.util.List;


public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllByActiveTrue();

}
