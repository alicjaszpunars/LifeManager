package pl.coderslab.lifemanager.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "saving_PPK")
public class SavingPPK extends Saving {

    private String comment;

    public static final String TYPE = "PPK";

    @Override
    public String getSavingType() {
        return TYPE;
    }


}

