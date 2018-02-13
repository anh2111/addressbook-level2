package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "add John Doe p/98765432 e/johnd@gmail.com a/John street, block 123, #01-01";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should follow format BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    private boolean isPrivate;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postal;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.isPrivate = isPrivate;
        this.value = trimmedAddress;

        String[] parsedAddress = address.split(",");
        if (parsedAddress.length != 4) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.block = new Block(parsedAddress[0].trim());
        this.street = new Street(parsedAddress[1].trim());
        this.unit = new Unit(parsedAddress[2].trim());
        this.postal = new PostalCode(parsedAddress[3].trim());
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    class Block {
        public final String value;

        public Block(String value) {
            this.value = value;
        }
    }

    class Street {
        public final String value;

        public Street(String value) {
            this.value = value;
        }
    }

    class Unit {
        public final String value;

        public Unit(String value) {
            this.value = value;
        }
    }

    class PostalCode {
        public final String value;

        public PostalCode(String value) {
            this.value = value;
        }
    }
}