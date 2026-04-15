package pl.coderslab.lifemanager.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero(message = "must be positive value")
    private Double amount;

    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "expense_category_id", nullable = false)
    private ExpenseCategory category;


    @ManyToOne(optional = false)
    @JoinColumn(name = "daily_entry_id", nullable = false)
    private DailyEntry dailyEntry;


}

