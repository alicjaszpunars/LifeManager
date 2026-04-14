package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.lifemanager.dto.CategorySumDto;
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

    //sumowanie po kategoriach dla dnia

    @Query("""
                SELECT new pl.coderslab.lifemanager.dto.CategorySumDto(
                    e.category.category,
                    SUM (e.amount)
                )
                FROM Expense e
                WHERE e.dailyEntry = :entry
                GROUP BY e.category.category
            """)
    List<CategorySumDto> ExpenseCategoryForDay(@Param("entry") DailyEntry entry);

    //sumowanie po kategoriach dla okresu
    @Query("""
                SELECT new pl.coderslab.lifemanager.dto.CategorySumDto(
                    e.category.category,
                    SUM (e.amount)
                )
                FROM Expense e
                WHERE e.dailyEntry.date BETWEEN :startDate AND :endDate
                GROUP BY e.category.category
            """)
    List<CategorySumDto> sumExpenseCategoryForPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
