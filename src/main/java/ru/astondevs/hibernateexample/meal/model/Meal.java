package ru.astondevs.hibernateexample.meal.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Represents a meal entity stored in the "meals" table.
 * <p>
 * This class is annotated with JPA and used by Hibernate for ORM mapping.
 * </p>
 */
@Entity
@Table(name = "meals")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Double kkal;

    @Column(nullable = false)
    Double protein;

    @Column(nullable = false)
    Double fats;

    @Column(nullable = false)
    Double carb;
}
