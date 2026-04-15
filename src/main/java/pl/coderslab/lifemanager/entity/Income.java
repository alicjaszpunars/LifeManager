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
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_incomes;

    @NotNull
    @PositiveOrZero(message = "must be positive value")
    private Double amount;

    private String comment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "income_category_id", nullable = false)
    private IncomeCategory incomeCategory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "daily_entry_id")
    private DailyEntry dailyEntry;

}

