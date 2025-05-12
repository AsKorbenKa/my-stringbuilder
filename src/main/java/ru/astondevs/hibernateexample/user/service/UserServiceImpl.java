package ru.astondevs.hibernateexample.user.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.astondevs.hibernateexample.exception.DuplicatedDataException;
import ru.astondevs.hibernateexample.user.dto.UserDto;
import ru.astondevs.hibernateexample.user.dto.UserShortDto;
import ru.astondevs.hibernateexample.user.mapper.UserMapper;
import ru.astondevs.hibernateexample.user.model.User;
import ru.astondevs.hibernateexample.user.repository.UserRepository;

import java.util.Optional;

/**
 * Implementation of the {@link UserService} interface that handles user-related business logic.
 * <p>
 * This service provides functionality to create new users and ensures that
 * duplicate email addresses are not allowed in the system.
 * </p>
 */
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user in the system.
     * <p>
     * If a user with the same email already exists, a {@link DuplicatedDataException} is thrown.
     * </p>
     *
     * @param user the user data to create
     * @return a short-form DTO representing the saved user
     * @throws DuplicatedDataException if a user with the provided email already exists
     */
    @Override
    @Transactional
    public UserShortDto create(UserDto user) {
        log.debug("Добавляем нового пользователя в базу данных.");

        // Проверяем есть ли такой пользователь в бд
        if (getUserByEmail(user.getEmail()).isPresent()) {
            throw new DuplicatedDataException("Пользователь с email " + user.getEmail() + " уже существует.");
        }

        User savedUser = userRepository.save(UserMapper.mapUserDtoToUser(user));

        return UserMapper.mapUserToUserShortDto(savedUser);
    }

    /**
     * Retrieves a user by their email address, if present.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the user if found, or empty if not
     */
    @Transactional(readOnly = true)
    private Optional<User> getUserByEmail(String email) {
        log.debug("Получаем данные пользователя по его email.");
        return userRepository.findByEmail(email);
    }
}
