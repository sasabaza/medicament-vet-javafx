package fr.medicamentvet.gui.simple;

import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * The aim of the class is to create a custom constructor that extends Tab control.
 */
public class TabClass extends Tab {

    /**
     * Tab constructor
     *
     * @param text Text of the tab
     * @param node Node content
     */
    public TabClass(String text, Node node) {
        super(text, node);
        setClosable(false);
    }
}
