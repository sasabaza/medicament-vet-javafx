package fr.medicamentvet.gui.menu;

import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;

/**
 * The class helps to create a customized menu item.
 */
public class MenuItemClass extends MenuItem {

    /**
     * MenuItem constructor
     *
     * @param text               Text of the menu item
     * @param imageView          Graphic of the menu item
     * @param keyCodeCombination Key combination to trigger action
     */
    public MenuItemClass(String text, ImageView imageView, KeyCodeCombination keyCodeCombination) {
        super(text, imageView);
        setAccelerator(keyCodeCombination);
    }
}