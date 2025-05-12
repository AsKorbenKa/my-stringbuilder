package ru.astondevs.hibernateexample.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.astondevs.hibernateexample.meal.model.Meal;

import java.util.Optional;

/**
 * Repository interface for {@link Meal} entity operations.
 * <p>
 * Extends {@link JpaRepository} to provide basic CRUD functionality,
 * and defines a custom method to find a meal by its name.
 * </p>
 */
public interface MealRepository extends JpaRepository<Meal, Long> {
    /**
     * Finds a meal by its name.
     *
     * @param name the name of the meal to search for
     * @return an {@link Optional} containing the meal if found, or empty if not
     */
    Optional<Meal> findByName(String name);
}
