package fr.medicamentvet.gui.simple;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

/**
 * The aim of the class is to create a custom constructor that extends ToggleButton control.
 */
public class ToggleButtonClass extends ToggleButton {

    /**
     * ToggleButton constructor
     *
     * @param text      Text of the toggle button
     * @param imageView ImageView object holding an image
     */
    public ToggleButtonClass(String text, ImageView imageView) {
        super(text);
        setGraphic(imageView);
    }
}
