package pl.coderslab.lifemanager.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SummaryDto {

    private List<HabitViewSummaryDto> habits;


    private List<CategorySumDto> incomes;
    private List<CategorySumDto> expenses;


    private List<SavingSummaryDto> savings;
}
