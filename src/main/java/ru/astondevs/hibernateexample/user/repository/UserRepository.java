package ru.astondevs.hibernateexample.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.astondevs.hibernateexample.user.model.User;

import java.util.Optional;

/**
 * Repository interface for {@link User} entity operations.
 * <p>
 * Extends {@link JpaRepository} to provide standard CRUD functionality.
 * </p>
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the user if found, or empty if not
     */
    Optional<User> findByEmail(String email);
}
