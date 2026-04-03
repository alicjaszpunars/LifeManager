package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class SavingDepositsCreateDto {

    private String name;

    @NotNull
    @Positive
    private double amount;

    private LocalDate startDate;

    private String comment;
}
