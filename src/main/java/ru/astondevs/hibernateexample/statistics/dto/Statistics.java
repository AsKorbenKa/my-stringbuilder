package ru.astondevs.hibernateexample.statistics.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * A data model representing daily meal and calorie statistics for a user.
 * <p>
 * This class is typically used for reporting and analytics purposes.
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistics {
    String dailyCalorieIntake;
    String eatenKkalAndNumberOfMeals;
    String hasTheGoalBeenAchieved;
}
