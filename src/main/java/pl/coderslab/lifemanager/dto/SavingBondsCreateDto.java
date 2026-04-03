package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class SavingBondsCreateDto {
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    @Positive
    private int durationYears;

    private LocalDate startDate;

    private String comment;


}
