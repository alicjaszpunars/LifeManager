package pl.coderslab.lifemanager.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SavingsPPKCreateDto {

    private String name;

    private String comment;

    private LocalDate startDate;
}
