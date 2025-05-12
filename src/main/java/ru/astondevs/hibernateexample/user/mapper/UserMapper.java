package ru.astondevs.hibernateexample.user.mapper;

import ru.astondevs.hibernateexample.user.dto.UserDto;
import ru.astondevs.hibernateexample.user.dto.UserShortDto;
import ru.astondevs.hibernateexample.user.model.User;

/**
 * Utility class for mapping between {@link User}, {@link UserDto}, and {@link UserShortDto}.
 * <p>
 * Provides static methods to convert user-related data objects for use in different layers of the application.
 * </p>
 */
public class UserMapper {
    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     *
     * @param userDto the user DTO to convert
     * @return a {@link User} entity containing the same data
     */
    public static User mapUserDtoToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getGender(),
                userDto.getEmail(),
                userDto.getAge(),
                userDto.getWeightInKg(),
                userDto.getHeightInCm(),
                userDto.getGoal()
        );
    }

    /**
     * Converts a {@link User} entity to a {@link UserShortDto}.
     *
     * @param user the user entity to convert
     * @return a short-form DTO containing selected user data
     */
    public static UserShortDto mapUserToUserShortDto(User user) {
        return new UserShortDto(
                user.getName(),
                user.getGender(),
                user.getEmail(),
                user.getAge(),
                user.getWeightInKg(),
                user.getHeightInCm(),
                user.getGoal()
        );
    }
}
