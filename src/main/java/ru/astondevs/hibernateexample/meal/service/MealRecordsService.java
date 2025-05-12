package ru.astondevs.hibernateexample.meal.service;

import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealRecordsDto;

import java.util.List;

/**
 * Service interface for managing meal records.
 * <p>
 * Defines the contract for creating meal records based on a user's ID and the meals they consumed.
 * </p>
 */
public interface MealRecordsService {
    MealRecordsDto createMealRecords(Long userId, List<MealDto> eatenMeal);
}
