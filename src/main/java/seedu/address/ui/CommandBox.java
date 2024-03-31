package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory;

    private int commandHistoryIndex;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandHistory = new ArrayList<>();
        this.commandHistoryIndex = 0;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandHistory.add(commandText);
            commandHistoryIndex = commandHistory.size(); // Reset index.

            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles any key button pressed event. Currently supports only UP and DOWN keys.
     *
     * @param keyEvent The key pressed while command box is in focus.
     */
    @FXML
    private void handleKeyPressed(KeyEvent keyEvent) {
        final KeyCode keyCode = keyEvent.getCode();

        if (keyCode == KeyCode.UP) {
            commandTextField.setText(getPreviousCommandHistory(commandTextField.getText()));
        } else if (keyCode == KeyCode.DOWN) {
            commandTextField.setText(getNextCommandHistory(commandTextField.getText()));
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Get the next command in the command history. If the cursor is already at the last command,
     * then an empty string will be returned. If the command history is empty, then the given
     * {@code defaultText} will be returned instead.
     */
    private String getNextCommandHistory(String defaultText) {
        if (commandHistory.isEmpty()) {
            return defaultText;
        } else {
            commandHistoryIndex = Math.min(commandHistoryIndex + 1, commandHistory.size());
            return (commandHistoryIndex == commandHistory.size())
                ? ""
                : commandHistory.get(commandHistoryIndex);
        }
    }

    /**
     * Get the previous command in the command history. If the command history is empty, then the given
     * {@code defaultText} will be returned instead.
     */
    private String getPreviousCommandHistory(String defaultText) {
        if (commandHistory.isEmpty()) {
            return defaultText;
        } else {
            commandHistoryIndex = Math.max(commandHistoryIndex - 1, 0);
            return commandHistory.get(commandHistoryIndex);
        }
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
