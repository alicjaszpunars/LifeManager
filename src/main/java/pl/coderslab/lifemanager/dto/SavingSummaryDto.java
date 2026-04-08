package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.lifemanager.entity.Saving;
import pl.coderslab.lifemanager.entity.SavingPPK;

@Getter
@Setter
public class SavingSummaryDto {
    private String savingType;
    private Double startAmount;
    private Double endAmount;
    private Double changeAmount;
    private Double percentage;



}
