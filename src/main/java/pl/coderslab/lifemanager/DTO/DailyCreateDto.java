package pl.coderslab.lifemanager.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.lifemanager.entity.Mood;

import java.time.LocalDate;

@Getter
@Setter
public class DailyCreateDto {

    @NotNull
    private LocalDate date;
    private Mood mood;
    private String diary;
}
