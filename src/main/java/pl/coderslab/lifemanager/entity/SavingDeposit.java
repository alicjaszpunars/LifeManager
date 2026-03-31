package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_deposits")
public class SavingDeposit extends Saving {
    private int duration_days; //w dniach na jaki czas lokata

    public LocalDate endDate() {
        return getStartDate().plusDays(duration_days);
    }
    private String comments;
}
