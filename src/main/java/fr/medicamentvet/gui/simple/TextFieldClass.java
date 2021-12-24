package fr.medicamentvet.gui.simple;

import fr.medicamentvet.utils.Static;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;

/**
 * The aim of the class is to create a custom constructor that extends TextField control.
 */
public class TextFieldClass extends TextField {

    /**
     * TextField constructor
     *
     * @param promptText Prompt text to display
     * @param className  CSS class name
     */
    public TextFieldClass(String promptText, String className) {
        setPromptText(promptText);
        setContextMenu(new ContextMenu());
        getStyleClass().add(className);

        focusedProperty().addListener((observableValue, isNotFocused, isFocused) -> {
            if (isFocused) {
                getStyleClass().removeAll(Static.ERROR_FIELD_CLASS_TEXT);
            }
        });
    }
}
