package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitViewDailyDto {
    private String habitName;
    private Boolean done;

    public HabitViewDailyDto(String habitName, Boolean done) {
        this.habitName = habitName;
        this.done = done;
    }
}
