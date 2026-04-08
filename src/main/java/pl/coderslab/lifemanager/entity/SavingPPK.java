package pl.coderslab.lifemanager.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_PPK")
public class SavingPPK  extends Saving{

    private String comment;


    public static final String TYPE = "PPK";

    @Override
    public String getSavingType() {
        return TYPE;
    }


}

