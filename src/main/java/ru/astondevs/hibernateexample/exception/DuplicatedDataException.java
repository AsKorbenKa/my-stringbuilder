package ru.astondevs.hibernateexample.exception;

/**
 * Exception thrown when attempting to create or insert data that already exists and must be unique.
 * <p>
 * Typically used for duplicate entries such as email addresses or usernames.
 * </p>
 */
public class DuplicatedDataException extends RuntimeException {
    public DuplicatedDataException(final String message) {
        super(message);
    }
}
