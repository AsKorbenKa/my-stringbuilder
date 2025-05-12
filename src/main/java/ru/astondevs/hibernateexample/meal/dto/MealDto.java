package ru.astondevs.hibernateexample.meal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * A Data Transfer Object (DTO) for the {@link ru.astondevs.hibernateexample.meal.model.Meal} entity.
 * <p>
 * Used to transfer meal data between layers without exposing the entity directly.
 * </p>
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealDto {
    Long id;
    String name;
    Double kkal;
    Double protein;
    Double fats;
    Double carb;
}
