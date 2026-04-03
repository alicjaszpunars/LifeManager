package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class SavingValueCreateDto {

    @NotNull
    private LocalDate date;

    @NotNull
    @Positive
    private  double amount;

}
