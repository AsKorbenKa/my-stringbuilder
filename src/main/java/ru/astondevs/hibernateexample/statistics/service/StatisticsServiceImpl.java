package ru.astondevs.hibernateexample.statistics.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astondevs.hibernateexample.exception.NotFoundException;
import ru.astondevs.hibernateexample.meal.model.MealRecords;
import ru.astondevs.hibernateexample.meal.repository.MealRecordsRepository;
import ru.astondevs.hibernateexample.statistics.dto.Statistics;
import ru.astondevs.hibernateexample.statistics.mapper.StatisticsMapper;
import ru.astondevs.hibernateexample.user.enums.UserGender;
import ru.astondevs.hibernateexample.user.model.User;
import ru.astondevs.hibernateexample.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementation of the {@link StatisticsService} interface that handles user-related business logic.
 * <p>
 * This service provides functionality to create new users stats.
 * </p>
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsServiceImpl implements StatisticsService {
    MealRecordsRepository mealRecordsRepository;
    UserRepository userRepository;

    @Autowired
    public StatisticsServiceImpl(MealRecordsRepository mealRecordsRepository, UserRepository userRepository) {
        this.mealRecordsRepository = mealRecordsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves statistics for a user based on the provided user ID and date.
     * The statistics include the user's daily calorie intake, total calories eaten,
     * the number of meals, and whether the user has achieved their goal.
     *
     * @param userId The unique identifier of the user whose statistics are requested.
     * @param date The date for which the statistics are requested.
     * @return A {@link Statistics} object containing the user's statistics for the given date.
     */
    @Override
    @Transactional(readOnly = true)
    public Statistics getUserStatistics(Long userId, LocalDate date) {
        log.debug("Получаем статистику пользователя с id {} по дате {}.", userId, date);
        User user = findUserById(userId);
        double dailyCalorieIntake = countDailyCalorieIntake(user);
        List<MealRecords> eatenKkalRecords = mealRecordsRepository.findAllByUserIdAndMealDate(userId, date);

        if (!eatenKkalRecords.isEmpty()) {
            double eatenKkal = eatenKkalRecords.stream().map(MealRecords::getEatenKkal)
                    .reduce(Double::sum).get();
            int numberOfMeals = eatenKkalRecords.size();
            return StatisticsMapper.mapUserDataToStatistics(dailyCalorieIntake, eatenKkal, numberOfMeals, user.getGoal(),
                    date);
        }
        return StatisticsMapper.mapUserDataToStatistics(dailyCalorieIntake, 0, 0, user.getGoal(),
                date);
    }

    /**
     * Finds a user by their unique ID.
     *
     * @param userId The unique identifier of the user.
     * @return The {@link User} object if the user is found.
     * @throws NotFoundException If the user with the given ID is not found.
     */
    private User findUserById(Long userId) {
        log.debug("Проверяем существует ли пользователь по id {}.", userId);
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Ошибка при получении статистики. Пользователь с id "
                        + userId + " не найден."));
    }

    /**
     * Calculates the daily calorie intake requirement for a user based on their gender, weight, height, and age.
     * Uses the Mifflin-St Jeor formula to compute the BMR (Basal Metabolic Rate) and adjusts for activity level.
     *
     * @param user The {@link User} for whom the daily calorie intake is being calculated.
     * @return The daily calorie intake in kilocalories.
     */
    private double countDailyCalorieIntake(User user) {
        log.debug("Высчитывает дневную норму калорий пользователя {}.", user);
        return switch (user.getGender()) {
            case UserGender.MALE -> 88.36 + (13.4 * user.getWeightInKg()) + (4.8 * user.getHeightInCm()) -
                    (5.7 * user.getAge());
            case UserGender.FEMALE -> 447.6 + (9.2 * user.getWeightInKg()) + (3.1 * user.getHeightInCm()) -
                    (4.3 * user.getAge());
        };
    }
}
