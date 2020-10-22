package seedu.fma.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.fma.commons.core.GuiSettings;
import seedu.fma.commons.core.LogsCenter;
import seedu.fma.logic.commands.Command;
import seedu.fma.logic.commands.CommandResult;
import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.logic.parser.FixMyAbsParser;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.Model;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.log.Log;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FixMyAbsParser fixMyAbsParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        fixMyAbsParser = new FixMyAbsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = fixMyAbsParser.parseCommand(commandText, model.getLogBook());
        commandResult = command.execute(model);

        try {
            storage.saveLogBook(model.getLogBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLogBook getLogBook() {
        return model.getLogBook();
    }

    @Override
    public ObservableList<Log> getFilteredLogList() {
        return model.getFilteredLogList();
    }

    @Override
    public ObservableList<Exercise> getExerciseList() {
        return model.getExerciseList();
    }

    @Override
    public Path getLogBookFilePath() {
        return model.getLogBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
