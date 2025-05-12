package ru.astondevs.hibernateexample.meal.mapper;

import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealShortDto;
import ru.astondevs.hibernateexample.meal.model.Meal;

/**
 * Utility class for mapping between {@link Meal}, {@link MealDto}, and {@link MealShortDto}.
 * <p>
 * Provides static methods to convert meal-related data objects for use in different layers of the application.
 * </p>
 */
public class MealMapper {
    /**
     * Converts a {@link MealDto} to a {@link Meal} entity.
     *
     * @param mealDto the meal DTO to convert
     * @return a {@link Meal} entity containing the same data
     */
    public static Meal mapMealDtoToMeal(MealDto mealDto) {
        return new Meal(
                mealDto.getId(),
                mealDto.getName(),
                mealDto.getKkal(),
                mealDto.getProtein(),
                mealDto.getFats(),
                mealDto.getCarb()
        );
    }

    /**
     * Converts a {@link Meal} to a {@link MealShortDto} entity.
     *
     * @param meal the meal DTO to convert
     * @return a {@link MealShortDto} entity containing the same data
     */
    public static MealShortDto mapMealToMealShortDto(Meal meal) {
        return new MealShortDto(
                meal.getName(),
                meal.getKkal(),
                meal.getProtein(),
                meal.getFats(),
                meal.getCarb()
        );
    }
}
