package pl.coderslab.lifemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitCreateDto {
    @NotBlank
    private String name;
}
