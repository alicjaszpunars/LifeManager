package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.ExpenseCategory;

import java.util.Optional;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    Optional<ExpenseCategory> findByCategory(String name);
//Optional jasno komunikuje, że encja może nie istnieć w bazie danych. Wymusza to świadomą
// obsługę tego przypadku i eliminuje możliwość wystąpienia błędów typu NullPointerException.

}
