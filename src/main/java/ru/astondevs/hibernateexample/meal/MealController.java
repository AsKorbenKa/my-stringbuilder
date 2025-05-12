package ru.astondevs.hibernateexample.meal;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealRecordsDto;
import ru.astondevs.hibernateexample.meal.dto.MealShortDto;
import ru.astondevs.hibernateexample.meal.service.MealRecordsService;
import ru.astondevs.hibernateexample.meal.service.MealService;

import java.util.List;

/**
 * REST controller for handling meal-related HTTP requests.
 * <p>
 * Provides endpoints for creating meals and recording consumed meals for users.
 * </p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/meal")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealController {
    MealService mealService;
    MealRecordsService mealRecordsService;

    /**
     * Creates a new meal in the system.
     *
     * @param mealDto the meal data to be created
     * @return a {@link MealShortDto} representing the newly created meal
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealShortDto create(@RequestBody MealDto mealDto) {
        return mealService.create(mealDto);
    }

    /**
     * Records a list of meals consumed by a specific user.
     *
     * @param eatenMeal the list of consumed meals
     * @param userId    the ID of the user who consumed the meals
     * @return a {@link MealRecordsDto} representing the meal record entry
     */
    @PostMapping("/{userId}")
    public MealRecordsDto createMealRecords(@RequestBody List<MealDto> eatenMeal,
                                            @PathVariable("userId") Long userId) {
        return mealRecordsService.createMealRecords(userId, eatenMeal);
    }
}
