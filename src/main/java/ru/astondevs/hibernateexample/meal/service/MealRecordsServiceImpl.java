package ru.astondevs.hibernateexample.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astondevs.hibernateexample.exception.NotFoundException;
import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealRecordsDto;
import ru.astondevs.hibernateexample.meal.mapper.MealRecordsMapper;
import ru.astondevs.hibernateexample.meal.model.MealRecords;
import ru.astondevs.hibernateexample.meal.repository.MealRecordsRepository;
import ru.astondevs.hibernateexample.user.model.User;
import ru.astondevs.hibernateexample.user.repository.UserRepository;

import java.util.List;

/**
 * Implementation of the {@link MealRecordsService} interface that handles the business logic
 * for creating and storing meal records for users.
 * <p>
 * This service verifies user existence, calculates total calories, and persists the data
 * using the {@link MealRecordsRepository}.
 * </p>
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealRecordsServiceImpl implements MealRecordsService {
    MealRecordsRepository mealRecordsRepository;
    UserRepository userRepository;

    @Autowired
    public MealRecordsServiceImpl(MealRecordsRepository mealRecordsRepository,
                                  UserRepository userRepository) {
        this.mealRecordsRepository = mealRecordsRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new meal record for the specified user based on the list of meals they consumed.
     * <p>
     * The method checks if the user exists, calculates the total calories from the meal list,
     * saves the meal record to the database, and returns the corresponding DTO.
     * </p>
     *
     * @param userId     the ID of the user
     * @param eatenMeal  the list of meals consumed by the user
     * @return a {@link MealRecordsDto} representing the created meal record
     * @throws NotFoundException if the user with the given ID does not exist
     */
    @Override
    @Transactional
    public MealRecordsDto createMealRecords(Long userId, List<MealDto> eatenMeal) {
        User user = findUserById(userId);
        Double eatenKkal = eatenMeal.stream()
                .map(MealDto::getKkal).reduce(Double::sum).get();
        MealRecords mealRecords = mealRecordsRepository.save(
                MealRecordsMapper.mapMealRecordsDtoToMealRecords(user, eatenKkal));
        return MealRecordsMapper.mapMealRecordsToMealRecordsDto(mealRecords);
    }

    /**
     * Retrieves a user by ID or throws a {@link NotFoundException} if not found.
     *
     * @param userId the ID of the user
     * @return the {@link User} entity
     * @throws NotFoundException if the user does not exist
     */
    private User findUserById(Long userId) {
        log.debug("Проверяем существует ли пользователь по id {}.", userId);
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Ошибка при добавлении съеденных калорий пользователя. Пользователь с id "
                        + userId + " не найден."));
    }
}
