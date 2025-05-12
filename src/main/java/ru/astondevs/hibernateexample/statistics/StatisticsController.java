package ru.astondevs.hibernateexample.statistics;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.astondevs.hibernateexample.statistics.dto.Statistics;
import ru.astondevs.hibernateexample.statistics.service.StatisticsService;

import java.time.LocalDate;

/**
 * REST controller for managing user stats-related HTTP requests.
 * <p>
 * Handles user stats creation via POST endpoint and delegates logic to the {@link StatisticsService}.
 * </p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/statistics/{userId}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticsController {
    StatisticsService statisticsService;

    /**
     * Handles GET requests to retrieve the statistics for a specific user on a given date.
     *
     * @param userId The unique identifier of the user whose statistics are being requested.
     * @param date The date for which the statistics are being retrieved.
     * @return A {@link Statistics} object containing the user's statistics for the given date.
     */
    @GetMapping
    public Statistics getUserStatistics(@PathVariable("userId") Long userId,
                                        @RequestParam(name = "date") LocalDate date) {
        return statisticsService.getUserStatistics(userId, date);
    }
}
