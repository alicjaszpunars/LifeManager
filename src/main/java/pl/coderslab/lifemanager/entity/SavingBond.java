package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_bonds")
public class SavingBond extends Saving {


    @Positive
    private int quantity;
    @Positive
    private int durationYears;

    public LocalDate maturityDate90() {
        return getStartDate().plusYears(durationYears);
    }

    private String comments;
}
