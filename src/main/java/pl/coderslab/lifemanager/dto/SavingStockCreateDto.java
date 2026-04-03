package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class SavingStockCreateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String ticker;

    @NotNull
    @Positive
    private double quantity;

    @NotBlank
    private String currencyCode;

    private LocalDate startDate;

    private String comment;


}
