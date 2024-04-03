package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is creating a person with an invalid address.
 */
public class InvalidAddressException extends RuntimeException {
    public InvalidAddressException(String message) {
        super(message);
    }
}
