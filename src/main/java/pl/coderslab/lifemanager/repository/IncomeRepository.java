package pl.coderslab.lifemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.lifemanager.dto.CategorySumDto;
import pl.coderslab.lifemanager.entity.DailyEntry;
import pl.coderslab.lifemanager.entity.Expense;
import pl.coderslab.lifemanager.entity.Income;
import pl.coderslab.lifemanager.entity.IncomeCategory;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository <Income, Long> {
    List<Income> findAllByDailyEntry(DailyEntry dailyEntry);

    List<Income> findAllByIncomeCategory(IncomeCategory incomeCategory);

    List<Income> findAllByDailyEntry_Date_Between(LocalDate startDate, LocalDate endDate);

    //sumowanie po kategoriach -> reczna kwerenda

    @Query("""
    SELECT new pl.coderslab.lifemanager.dto.CategorySumDto(
        i.incomeCategory.category,
        SUM (i.amount)
    )
    FROM Income i
    WHERE i.dailyEntry = :entry
    GROUP BY i.incomeCategory.category
""")
    List<CategorySumDto> IncomeCategoryForDay(@Param("entry") DailyEntry entry);


}
