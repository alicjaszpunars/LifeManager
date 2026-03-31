package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.DailyEntry;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyEntryRepository extends JpaRepository<DailyEntry, Long> {

    Optional<DailyEntry> findByDate(LocalDate date);
}
