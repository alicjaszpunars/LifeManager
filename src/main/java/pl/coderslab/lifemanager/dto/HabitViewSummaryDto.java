package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitViewSummaryDto {
    private  String habitName;
    private int daysDone;

    public HabitViewSummaryDto(String habitName, int daysDone) {
        this.habitName = habitName;
        this.daysDone = daysDone;
    }

}
