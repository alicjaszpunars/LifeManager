package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.Saving;

public interface SavingsRepository extends JpaRepository<Saving, Long> {
}
