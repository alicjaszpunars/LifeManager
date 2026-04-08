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

    private  double amount;
    private String comment;


    public static final String TYPE = "DEPOSIT";

    @Override
    public String getSavingType() {
        return TYPE;
    }

}
