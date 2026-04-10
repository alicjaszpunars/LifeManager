package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
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


    @Positive (message= "positive value only")
    private int quantity;
    @Positive(message= "positive value only")
    private int durationYears;

    public LocalDate maturityDate() {
        return getStartDate().plusYears(durationYears);
    }

    private String comment;


    public static final String TYPE = "BOND";

    @Override
    public String getSavingType() {
        return TYPE;
    }

}
