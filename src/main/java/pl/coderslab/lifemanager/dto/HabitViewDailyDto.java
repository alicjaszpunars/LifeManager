package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitViewDailyDto {
    private Long habitId;
    private String habitName;
    private Boolean done;

    public HabitViewDailyDto(Long habitId, String habitName, Boolean done) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.done = done;
    }
}
