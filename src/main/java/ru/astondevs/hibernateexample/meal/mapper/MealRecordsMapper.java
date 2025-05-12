package ru.astondevs.hibernateexample.meal.mapper;

import ru.astondevs.hibernateexample.meal.dto.MealRecordsDto;
import ru.astondevs.hibernateexample.meal.model.MealRecords;
import ru.astondevs.hibernateexample.user.mapper.UserMapper;
import ru.astondevs.hibernateexample.user.model.User;

import java.time.LocalDate;

/**
 * Utility class for mapping between {@link MealRecords} entities and their corresponding DTOs.
 * <p>
 * Provides static methods to convert meal record data between the entity layer and the DTO layer.
 * </p>
 */
public class MealRecordsMapper {
    /**
     * Converts input data into a new {@link MealRecords} entity.
     *
     * @param user       the user who consumed the meal
     * @param eatenKkal  the amount of calories consumed
     * @return a {@link MealRecords} entity with the current date
     */
    public static MealRecords mapMealRecordsDtoToMealRecords(User user, Double eatenKkal) {
        return new MealRecords(
                null,
                user,
                eatenKkal,
                LocalDate.now()
        );
    }

    /**
     * Converts a {@link MealRecords} entity into a {@link MealRecordsDto}.
     *
     * @param mealRecords the meal record entity to convert
     * @return a DTO containing user info, calories consumed, and the date of the meal
     */
    public static MealRecordsDto mapMealRecordsToMealRecordsDto(MealRecords mealRecords) {
        return new MealRecordsDto(
                UserMapper.mapUserToUserShortDto(mealRecords.getUser()),
                mealRecords.getEatenKkal(),
                mealRecords.getMealDate()
        );
    }
}
