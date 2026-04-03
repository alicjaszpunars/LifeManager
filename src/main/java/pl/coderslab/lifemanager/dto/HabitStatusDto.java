package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HabitStatusDto {
    @NotNull
    private Long habitId;
    @NotNull
    private LocalDate date;
    @NotNull
    private Boolean done;

}
