package fr.medicamentvet.gui.simple;

import javafx.scene.text.Text;

/**
 * The aim of the class is to create overloaded constructors that extends Text class.
 */
public class TextClass extends Text {

    /**
     * Text constructor
     *
     * @param className CSS class name
     */
    public TextClass(String className) {
        getStyleClass().add(className);
    }

    /**
     * Text constructor
     *
     * @param text      Text to display
     * @param className CSS class name
     */
    public TextClass(String text, String className) {
        super(text);
        getStyleClass().add(className);
    }
}
