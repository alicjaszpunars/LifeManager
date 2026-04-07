package pl.coderslab.lifemanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitViewSummaryDto {
    private Long habitId;
    private  String habitName;
    private int daysDone;
}
