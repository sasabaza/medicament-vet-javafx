package fr.medicamentvet.gui.menu;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.utils.Static;
import javafx.application.Platform;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

/**
 * The class creates a MenuBar control with specific attached action for each menu item.
 */
public class MenuBarClass extends MenuBar {

    private final MenuItemClass printMenuItem;
    private final MenuItemClass createPDFMenuItem;
    private final MenuItemClass updateMenuItem;
    private final MenuItemClass favoriteMenuItem;
    private final MenuItemClass historyMenuItem;
    private final MenuItemClass deleteMenuItem;
    private final MenuItemClass exitMenuItem;
    private final MenuItem aboutMenuItem;

    public MenuBarClass() {

        printMenuItem = new MenuItemClass(Static.MENU_ITEM_IMPRIMER_TEXT, new ImageView(Static.IMAGE_PRINT_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.I, KeyCombination.SHORTCUT_DOWN));
        createPDFMenuItem = new MenuItemClass(Static.MENU_ITEM_CREER_PDF_TEXT, new ImageView(Static.IMAGE_CREATE_PDF_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.P, KeyCombination.SHORTCUT_DOWN));
        updateMenuItem = new MenuItemClass(Static.MENU_ITEM_MISE_A_JOUR_TEXT, new ImageView(Static.IMAGE_UPDATE_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.M, KeyCombination.SHORTCUT_DOWN));
        favoriteMenuItem = new MenuItemClass(Static.MENU_ITEM_FAVORIS_TEXT, new ImageView(Static.IMAGE_FAVORITE_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN));
        historyMenuItem = new MenuItemClass(Static.MENU_ITEM_HISTORIQUE_TEXT, new ImageView(Static.IMAGE_HISTORY_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.H, KeyCombination.SHORTCUT_DOWN));
        deleteMenuItem = new MenuItemClass(Static.MENU_ITEM_SUPPRIMER_MEDICAMENT_TEXT, new ImageView(Static.IMAGE_DELETE_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        exitMenuItem = new MenuItemClass(Static.MENU_ITEM_QUITTER_TEXT, new ImageView(Static.IMAGE_EXIT_MENU[Controller.getTheme()]), new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));

        MenuClass themeMenu = new MenuClass(Static.MENU_THEME_TEXT, this);

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem lightMenuItem = new RadioMenuItem(Static.MENU_ITEM_LIGHT_TEXT);
        lightMenuItem.setToggleGroup(toggleGroup);
        RadioMenuItem darkMenuItem = new RadioMenuItem(Static.MENU_ITEM_DARK_TEXT);
        darkMenuItem.setToggleGroup(toggleGroup);

        if (Controller.getTheme() == 0) {
            lightMenuItem.setSelected(true);
        }
        if (Controller.getTheme() == 1) {
            darkMenuItem.setSelected(true);
        }

        themeMenu.getItems().addAll(lightMenuItem, darkMenuItem);

        MenuClass fileMenu = new MenuClass(Static.MENU_FICHIER_TEXT, this);
        fileMenu.getItems().addAll(printMenuItem, createPDFMenuItem, updateMenuItem, favoriteMenuItem, historyMenuItem, themeMenu, deleteMenuItem, new SeparatorMenuItem(), exitMenuItem);

        aboutMenuItem = new MenuItem(Static.MENU_ITEM_A_PROPOS_TEXT, new ImageView(Static.IMAGE_ABOUT_MENU[Controller.getTheme()]));

        MenuClass helpMenu = new MenuClass(Static.MENU_AIDE_TEXT, this);
        helpMenu.getItems().add(aboutMenuItem);

        getMenus().addAll(fileMenu, helpMenu);

        printMenuItem.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDF(true));
        createPDFMenuItem.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDF(false));
        updateMenuItem.setOnAction(actionEvent -> TabPaneClass.updateDataInput());
        favoriteMenuItem.setOnAction(actionEvent -> TabPaneClass.viewFavoriteAction());
        historyMenuItem.setOnAction(actionEvent -> TabPaneClass.viewHistoryAction());
        lightMenuItem.setOnAction(actionEvent -> TabPaneClass.setTheme(0));
        darkMenuItem.setOnAction(actionEvent -> TabPaneClass.setTheme(1));
        deleteMenuItem.setOnAction(actionEvent -> TabPaneClass.deleteMedicamentAction());
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        aboutMenuItem.setOnAction(actionEvent -> TabPaneClass.viewWindowAboutAction());

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        setOnMousePressed(mouseEvent -> requestFocus());
    }

    public void setTheme(int theme) {
        printMenuItem.setGraphic(new ImageView(Static.IMAGE_PRINT_MENU[theme]));
        createPDFMenuItem.setGraphic(new ImageView(Static.IMAGE_CREATE_PDF_MENU[theme]));
        updateMenuItem.setGraphic(new ImageView(Static.IMAGE_UPDATE_MENU[theme]));
        favoriteMenuItem.setGraphic(new ImageView(Static.IMAGE_FAVORITE_MENU[theme]));
        historyMenuItem.setGraphic(new ImageView(Static.IMAGE_HISTORY_MENU[theme]));
        deleteMenuItem.setGraphic(new ImageView(Static.IMAGE_DELETE_MENU[theme]));
        exitMenuItem.setGraphic(new ImageView(Static.IMAGE_EXIT_MENU[theme]));
        aboutMenuItem.setGraphic(new ImageView(Static.IMAGE_ABOUT_MENU[theme]));
    }
}
