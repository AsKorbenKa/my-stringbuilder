package ru.astondevs.hibernateexample.exception;

/**
 * Exception thrown when a requested resource is not found.
 * <p>
 * Commonly used in service or controller layers to indicate that an entity
 * with a given identifier does not exist in the system.
 * </p>
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(final String message) {
        super(message);
    }
}
