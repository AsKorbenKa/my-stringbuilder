package ru.astondevs.hibernateexample.user.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.astondevs.hibernateexample.user.enums.UserGender;
import ru.astondevs.hibernateexample.user.enums.UserGoals;

/**
 * A Data Transfer Object (DTO) for the {@link ru.astondevs.hibernateexample.user.model.User} entity.
 * <p>
 * Used to transfer user data between layers without exposing the entity directly.
 * </p>
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String name;
    UserGender gender;
    String email;
    Long age;
    Long weightInKg;
    Long heightInCm;
    UserGoals goal;
}
