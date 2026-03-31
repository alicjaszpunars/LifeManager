package pl.coderslab.lifemanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.ExpenseCategory;
import pl.coderslab.lifemanager.entity.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByDailyEntry(DailyEntry dailyEntry);

    List<Expense> findAllByCategory(ExpenseCategory category);

    List<Expense> findAllByDailyEntry_Date_Between(LocalDate startDate, LocalDate endDate);

}
