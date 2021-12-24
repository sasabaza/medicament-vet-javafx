package fr.medicamentvet.gui.simple;

import fr.medicamentvet.utils.Static;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * The aim of the class is to create overloaded constructors that extends Button control.
 */
public class ButtonClass extends Button {

    /**
     * Button constructor
     *
     * @param imageView ImageView object holding an image
     */
    public ButtonClass(ImageView imageView) {
        setGraphic(imageView);
    }

    /**
     * Button constructor
     *
     * @param text Text of the button
     */
    public ButtonClass(String text) {
        super(text);
        getStyleClass().add(Static.BUTTON_ACTION_CLASS_TEXT);
    }

    /**
     * Button constructor
     *
     * @param text      Text of the button
     * @param imageView ImageView object holding an image
     */
    public ButtonClass(String text, ImageView imageView) {
        super(text);
        setGraphic(imageView);
    }
}
