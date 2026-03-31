package pl.coderslab.lifemanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_currency")
public class SavingCurrency extends  Saving{
    private String currencyCode;
    private  double quantity;
    private String comments;
}
