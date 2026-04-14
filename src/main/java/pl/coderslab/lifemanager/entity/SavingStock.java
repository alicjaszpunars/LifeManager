package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_stock")
public class SavingStock extends Saving {
    private String ticker;
    private String currencyCode;
    @Positive(message= "positive value only")
    private double quantity;
    private String comment;


    public static final String TYPE = "STOCK";

    @Override
    public String getSavingType() {
        return TYPE;
    }

    public double calculateValue(double stockPrice, double currencyRate){
        return quantity* stockPrice * currencyRate;
    }

}
