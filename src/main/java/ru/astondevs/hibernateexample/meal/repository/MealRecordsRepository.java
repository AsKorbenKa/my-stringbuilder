package ru.astondevs.hibernateexample.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.astondevs.hibernateexample.meal.model.MealRecords;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for {@link MealRecords} entity operations.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD functionality,
 * and defines a custom query method for retrieving meal records by user ID and date.
 * </p>
 */
public interface MealRecordsRepository extends JpaRepository<MealRecords, Long> {
    /**
     * Retrieves all meal records for a specific user on a specific date.
     *
     * @param userId    the ID of the user
     * @param mealDate  the date of the meal
     * @return a list of {@link MealRecords} matching the criteria
     */
    List<MealRecords> findAllByUserIdAndMealDate(Long userId, LocalDate mealDate);
}
