package ru.astondevs.hibernateexample.meal.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astondevs.hibernateexample.exception.DuplicatedDataException;
import ru.astondevs.hibernateexample.meal.dto.MealDto;
import ru.astondevs.hibernateexample.meal.dto.MealShortDto;
import ru.astondevs.hibernateexample.meal.mapper.MealMapper;
import ru.astondevs.hibernateexample.meal.model.Meal;
import ru.astondevs.hibernateexample.meal.repository.MealRepository;

import java.util.Optional;

/**
 * Implementation of the {@link MealService} interface that handles business logic
 * related to meal creation and validation.
 * <p>
 * This service checks for duplicate meals by name and saves new meals to the database.
 * </p>
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MealServiceImpl implements MealService {
    MealRepository mealRepository;

    @Autowired
    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    /**
     * Creates a new meal in the system based on the provided {@link MealDto}.
     * <p>
     * If a meal with the same name already exists, a {@link DuplicatedDataException} is thrown.
     * </p>
     *
     * @param mealDto the meal data to create
     * @return a {@link MealShortDto} representing the saved meal
     * @throws DuplicatedDataException if a meal with the same name already exists
     */
    @Override
    @Transactional
    public MealShortDto create(MealDto mealDto) {
        log.debug("Добавляем новое блюдо в базу данных.");

        // Проверяем есть ли уже такое блюдо в базе данных
        if (getMealByName(mealDto.getName()).isPresent()) {
            throw new DuplicatedDataException("Блюдо с названием '" + mealDto.getName() + "' уже существует.");
        }

        Meal savedMeal = mealRepository.save(MealMapper.mapMealDtoToMeal(mealDto));

        return MealMapper.mapMealToMealShortDto(savedMeal);
    }

    /**
     * Retrieves a meal by its name if it exists.
     *
     * @param name the name of the meal
     * @return an {@link Optional} containing the meal if found, or empty if not
     */
    private Optional<Meal> getMealByName(String name) {
        log.debug("Проверяем есть ли в базе данных блюдо с названием {}.", name);
        return mealRepository.findByName(name);
    }
}
