package fr.medicamentvet.gui.menu;

import javafx.scene.control.Menu;

/**
 * The class creates a menu which is part of the MenuBar control.
 */
public class MenuClass extends Menu {

    public MenuClass(String text, MenuBarClass menuBarClass) {
        super(text);

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        setOnShowing(event -> menuBarClass.requestFocus());
    }
}