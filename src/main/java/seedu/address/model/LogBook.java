package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.log.Log;
import seedu.address.model.log.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameLog comparison)
 */
public class LogBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public LogBook() {}

    /**
     * Creates an LogBook using the Persons in the {@code toBeCopied}
     */
    public LogBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the log list with {@code logs}.
     * {@code logs} must not contain duplicate logs.
     */
    public void setPersons(List<Log> logs) {
        this.persons.setPersons(logs);
    }

    /**
     * Resets the existing data of this {@code LogBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// log-level operations

    /**
     * Returns true if a log with the same identity as {@code log} exists in the address book.
     */
    public boolean hasPerson(Log log) {
        requireNonNull(log);
        return persons.contains(log);
    }

    /**
     * Adds a log to the address book.
     * The log must not already exist in the address book.
     */
    public void addPerson(Log p) {
        persons.add(p);
    }

    /**
     * Replaces the given log {@code target} in the list with {@code editedLog}.
     * {@code target} must exist in the address book.
     * The log identity of {@code editedLog} must not be the same as another existing log in the address book.
     */
    public void setPerson(Log target, Log editedLog) {
        requireNonNull(editedLog);

        persons.setPerson(target, editedLog);
    }

    /**
     * Removes {@code key} from this {@code LogBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Log key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Log> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LogBook // instanceof handles nulls
                && persons.equals(((LogBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
