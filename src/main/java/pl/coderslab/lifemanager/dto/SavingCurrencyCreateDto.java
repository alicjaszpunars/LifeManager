package pl.coderslab.lifemanager.dto;

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
    private double quantity;

    private LocalDate startDate;

    private String comment;

}
