package ru.astondevs.hibernateexample.statistics.mapper;

import ru.astondevs.hibernateexample.statistics.dto.Statistics;
import ru.astondevs.hibernateexample.user.enums.UserGoals;

import java.time.LocalDate;

/**
 * This class provides a method to map user data related to calorie intake, meals, and goals to a
 * {@link Statistics} object. It calculates whether the user has achieved their set goal based on
 * their daily calorie intake and number of meals.
 */
public class StatisticsMapper {
    /**
     * Maps the user's data (daily calorie intake, eaten calories, number of meals, user goals, and date)
     * to a {@link Statistics} object, determining if the user has achieved their set goal for the day.
     *
     * @param dailyCalorieIntake The user's daily calorie intake goal in kilocalories.
     * @param eatenKkal The amount of calories the user has eaten during the day in kilocalories.
     * @param numberOfMeals The number of meals the user had during the day.
     * @param goal The user's weight-related goal, which can be one of the following:
     *             {@link UserGoals#GAIN_WEIGHT}, {@link UserGoals#KEEP_WEIGHT}, or {@link UserGoals#WEIGHT_LOSS}.
     * @param date The date the statistics are being recorded.
     * @return A {@link Statistics} object containing the user's calorie intake information, meal details,
     *         and whether their goal was achieved or not for the day.
     */
    public static Statistics mapUserDataToStatistics(double dailyCalorieIntake, double eatenKkal, int numberOfMeals,
                                                     UserGoals goal, LocalDate date) {
        String goalAchieved = "Вы достигли поставленной цели.";
        String goalNotAchieved = "Вы не достигли поставленной цели.";
        Statistics statistics = new Statistics();
        statistics.setDailyCalorieIntake("Ваша дневная норма калорий составляет " + dailyCalorieIntake + " ккал.");

        // Проверяем были ли записи о съеденных калориях за определенный день и выставляем соответствующее значение
        if (numberOfMeals == 0) {
            statistics.setEatenKkalAndNumberOfMeals(date + " вы не вносили данные о ваших приемах пищи.");
        } else {
            statistics.setEatenKkalAndNumberOfMeals(String.format(date + " вы съели %.2f ккал. " +
                    "Количество приемов пищи: " + numberOfMeals + ".", eatenKkal));
        }

        // Проверяем выполнена ли поставленная цель и выставляем соответствующее значение
        if (eatenKkal > dailyCalorieIntake) {
            if (goal.equals(UserGoals.GAIN_WEIGHT)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        } else if (eatenKkal == dailyCalorieIntake) {
            if (goal.equals(UserGoals.KEEP_WEIGHT)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        } else {
            if (goal.equals(UserGoals.WEIGHT_LOSS)) {
                statistics.setHasTheGoalBeenAchieved(goalAchieved);
            } else {
                statistics.setHasTheGoalBeenAchieved(goalNotAchieved);
            }
        }

        return statistics;
    }
}
