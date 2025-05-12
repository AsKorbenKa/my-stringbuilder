package ru.astondevs.hibernateexample.user.service;

import ru.astondevs.hibernateexample.user.dto.UserDto;
import ru.astondevs.hibernateexample.user.dto.UserShortDto;

/**
 * Service interface for user-related operations.
 * <p>
 * Defines the contract for creating users in the system.
 * </p>
 */
public interface UserService {
    UserShortDto create(UserDto user);
}
