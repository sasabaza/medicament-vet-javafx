package fr.medicamentvet.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * The class is the status bar component. It should display a message when there is an error.
 */
public class StatusBar extends HBox {

    private static Label label;

    public StatusBar() {
        label = new Label();

        getChildren().add(label);
    }

    public static void setLabelMessage(String text) {
        label.setText(text);
    }
}