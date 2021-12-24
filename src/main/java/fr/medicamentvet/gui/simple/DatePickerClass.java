package fr.medicamentvet.gui.simple;

import java.time.LocalDate;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;

/**
 * The aim of the class is to create a custom constructor that extends DatePicker control.
 */
public class DatePickerClass extends DatePicker {

    /**
     * DatePicker constructor
     *
     * @param initialLocalDate Initial LocalDate
     * @param promptText       Prompt text to display
     */
    public DatePickerClass(LocalDate initialLocalDate, String promptText) {
        setValue(initialLocalDate);
        setPromptText(promptText);
        setEditable(false);
        getEditor().setContextMenu(new ContextMenu());
    }
}
