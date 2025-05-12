package ru.astondevs.hibernateexample.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.astondevs.hibernateexample.user.enums.UserGender;
import ru.astondevs.hibernateexample.user.enums.UserGoals;

/**
 * Represents a user entity stored in the "users" table.
 * <p>
 * This class is annotated with JPA and used by Hibernate for ORM mapping.
 * It includes fields for personal information and fitness goals.
 * </p>
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Enumerated(EnumType.STRING)
    UserGender gender;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    Long age;

    @Column(name = "weight", nullable = false)
    Long weightInKg;

    @Column(name = "height", nullable = false)
    Long heightInCm;

    @Enumerated(EnumType.STRING)
    UserGoals goal;
}
