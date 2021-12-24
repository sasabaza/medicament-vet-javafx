package fr.medicamentvet.gui.simple;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

/**
 * The aim of the class is to create a custom constructor that extends ComboBox control.
 */
public class ComboBoxClass extends ComboBox<String> {

    /**
     * ComboBox constructor
     *
     * @param array Array of Strings
     * @param count Maximum number of rows to be visible
     */
    public ComboBoxClass(String[] array, int count) {
        setItems(FXCollections.observableArrayList(array));
        setVisibleRowCount(count);
    }
}
