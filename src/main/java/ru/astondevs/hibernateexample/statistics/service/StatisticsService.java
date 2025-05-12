package ru.astondevs.hibernateexample.statistics.service;

import ru.astondevs.hibernateexample.statistics.dto.Statistics;

import java.time.LocalDate;

/**
 * Service interface for statistics operations.
 * <p>
 * Defines the contract for creating user stats in the system.
 * </p>
 */
public interface StatisticsService {
    Statistics getUserStatistics(Long userId, LocalDate date);
}
