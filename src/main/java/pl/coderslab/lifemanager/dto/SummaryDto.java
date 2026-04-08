package pl.coderslab.lifemanager.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

//globalne dto do wyswietlania podsumowan okresowych, odpowiedzialnosci sa rozdzielone na rozne dto wczesniej, a to jest forma kontenera

public class SummaryDto {

    //nawyki
    private List<HabitViewSummaryDto> habits;

    //income i expensy
    private List<CategorySumDto> incomes;
    private List<CategorySumDto> expenses;

    //oszczednosci
    private List<SavingSummaryDto> savings;


}
