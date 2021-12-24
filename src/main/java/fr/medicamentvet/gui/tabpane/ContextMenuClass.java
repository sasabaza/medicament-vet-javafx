package fr.medicamentvet.gui.tabpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.utils.Static;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

/**
 * The purpose of the class is to create a popup menu with three items.
 */
public class ContextMenuClass extends ContextMenu {

    private final MenuItem copyMenuItem;
    private final MenuItem printMenuItem;
    private final MenuItem createPDFMenuItem;

    public ContextMenuClass() {
        copyMenuItem = new MenuItem(Static.COPIER_TEXT, new ImageView(Static.IMAGE_COPY_MENU[Controller.getTheme()]));
        printMenuItem = new MenuItem(Static.IMPRIMER_TEXT, new ImageView(Static.IMAGE_PRINT_MENU[Controller.getTheme()]));
        createPDFMenuItem = new MenuItem(Static.CREER_PDF_TEXT, new ImageView(Static.IMAGE_CREATE_PDF_MENU[Controller.getTheme()]));

        getItems().addAll(copyMenuItem, printMenuItem, createPDFMenuItem);

        copyMenuItem.setOnAction(actionEvent -> TabPaneClass.copyTextAction());
        printMenuItem.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDFCustomMenu(true));
        createPDFMenuItem.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDFCustomMenu(false));
    }

    public void setTheme(int theme) {
        copyMenuItem.setGraphic(new ImageView(Static.IMAGE_COPY_MENU[theme]));
        printMenuItem.setGraphic(new ImageView(Static.IMAGE_PRINT_MENU[theme]));
        createPDFMenuItem.setGraphic(new ImageView(Static.IMAGE_CREATE_PDF_MENU[theme]));
    }
}
