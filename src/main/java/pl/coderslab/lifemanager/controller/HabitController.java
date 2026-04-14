package pl.coderslab.lifemanager.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.lifemanager.dto.HabitCreateDto;
import pl.coderslab.lifemanager.dto.HabitStatusDto;
import pl.coderslab.lifemanager.entity.Habit;
import pl.coderslab.lifemanager.entity.HabitTracker;
import pl.coderslab.lifemanager.service.HabitService;

import java.time.LocalDate;
import java.util.List;
@Tag(name = "03. Habits ", description = "Habits and their statuses")
@RestController
@RequestMapping("/habit")
public class HabitController {

    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public Habit createHabit(@RequestBody HabitCreateDto dto) {
        return habitService.createHabit(dto);
    }

    @PostMapping("{date}")
    public HabitTracker setStatus(@RequestBody HabitStatusDto dto) {
        return habitService.setHabitTracker(dto);
    }


    @PostMapping("/{habitId}/deactive")
    public void deactivate(@PathVariable Long habitId) {
        habitService.deactivateHabit(habitId);
    }

    @GetMapping("/{date}")
    public List<HabitTracker> getDailyHabit(@PathVariable LocalDate date) {
        return habitService.dailyHabit(date);
    }

    @GetMapping("/{habitId}/period")
    public List<HabitTracker> getHabitPeriod
    (@PathVariable Long habitId,
     @RequestParam LocalDate start,
     @RequestParam LocalDate end) {
        return habitService.periodHabit(habitId, start, end);
    }

    @GetMapping
    public List<Habit> getActiveHabits() {
        return habitService.getActiveHabits();
    }

}
