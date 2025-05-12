package ru.astondevs.hibernateexample.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.astondevs.hibernateexample.user.dto.UserDto;
import ru.astondevs.hibernateexample.user.dto.UserShortDto;
import ru.astondevs.hibernateexample.user.service.UserService;

/**
 * REST controller for managing user-related HTTP requests.
 * <p>
 * Handles user creation via POST endpoint and delegates logic to the {@link UserService}.
 * </p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    /**
     * Creates a new user based on the provided request body.
     *
     * @param user the user data to create
     * @return a {@link UserShortDto} representing the created user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserShortDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }
}
