package ru.astondevs.hibernateexample.meal.service;

import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealShortDto;

/**
 * Service interface for managing meal-related operations.
 * <p>
 * Defines the contract for creating meals in the system.
 * </p>
 */
public interface MealService {
    MealShortDto create(MealDto mealDto);
}
