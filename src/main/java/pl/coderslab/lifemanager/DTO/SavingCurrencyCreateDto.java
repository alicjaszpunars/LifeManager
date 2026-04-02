package pl.coderslab.lifemanager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class SavingCurrencyCreateDto {

    @NotBlank
    private String currencyCode;

    @NotNull
    @Positive
    private double amount;

    private LocalDate startDate;

    private String comment;

}
