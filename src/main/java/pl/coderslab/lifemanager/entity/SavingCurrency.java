package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_currency")
public class SavingCurrency extends Saving {

    @NotNull
    private String currencyCode;

    @Positive(message = "positive value only")
    private double quantity;

    private String comment;

    public static final String TYPE = "CURRENCY";

    @Override
    public String getSavingType() {
        return TYPE;
    }

    public double calculateValue(double rate) {
        return rate * quantity;
    }

}
