package pl.coderslab.lifemanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.ExpenseCategory;
import pl.coderslab.lifemanager.entity.Expense;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    //pobieranie dla dnia
    List<Expense> findAllByDailyEntry(DailyEntry dailyEntry);

    //pobieranie dla kategorii
    List<Expense> findAllByCategory(ExpenseCategory category);

    //pobieranie dla dat od do
    List<Expense> findAllByDailyEntry_Date_Between(LocalDate startDate, LocalDate endDate);

}
