package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.log.Log;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final LogBook logBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Log> filteredLogs;

    /**
     * Initializes a ModelManager with the given logBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.logBook = new LogBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredLogs = new FilteredList<>(this.logBook.getPersonList());
    }

    public ModelManager() {
        this(new LogBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== LogBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.logBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return logBook;
    }

    @Override
    public boolean hasPerson(Log log) {
        requireNonNull(log);
        return logBook.hasPerson(log);
    }

    @Override
    public void deletePerson(Log target) {
        logBook.removePerson(target);
    }

    @Override
    public void addPerson(Log log) {
        logBook.addPerson(log);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Log target, Log editedLog) {
        requireAllNonNull(target, editedLog);

        logBook.setPerson(target, editedLog);
    }

    //=========== Filtered Log List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Log} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Log> getFilteredPersonList() {
        return filteredLogs;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Log> predicate) {
        requireNonNull(predicate);
        filteredLogs.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return logBook.equals(other.logBook)
                && userPrefs.equals(other.userPrefs)
                && filteredLogs.equals(other.filteredLogs);
    }

}
