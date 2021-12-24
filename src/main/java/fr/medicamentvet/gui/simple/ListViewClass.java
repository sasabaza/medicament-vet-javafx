package fr.medicamentvet.gui.simple;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * The aim of the class is to create a custom constructor that extends ListView control.
 */
public class ListViewClass extends ListView<String> {

    /**
     * ListView constructor
     *
     * @param selectionMode  Selection mode
     * @param cellSize       Cell size
     * @param observableList Observable list of Strings
     */
    public ListViewClass(SelectionMode selectionMode, double cellSize, ObservableList<String> observableList) {
        setItems(observableList);
        getSelectionModel().setSelectionMode(selectionMode);
        setFixedCellSize(cellSize);
    }
}
