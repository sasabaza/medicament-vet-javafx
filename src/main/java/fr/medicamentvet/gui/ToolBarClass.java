package fr.medicamentvet.gui;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.simple.ButtonClass;
import fr.medicamentvet.utils.Static;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;

/**
 * The class represents a toolbar component with buttons that perform an action.
 */
public class ToolBarClass extends ToolBar {

    private final ButtonClass printButton;
    private final ButtonClass createPDFButton;
    private final ButtonClass updateButton;
    private final ButtonClass favoriteButton;
    private final ButtonClass historyButton;
    private final ButtonClass deleteButton;

    public ToolBarClass() {
        printButton = new ButtonClass(new ImageView(Static.IMAGE_PRINT_TOOLBAR[Controller.getTheme()]));
        createPDFButton = new ButtonClass(new ImageView(Static.IMAGE_CREATE_PDF_TOOLBAR[Controller.getTheme()]));
        updateButton = new ButtonClass(new ImageView(Static.IMAGE_UPDATE_TOOLBAR[Controller.getTheme()]));
        favoriteButton = new ButtonClass(new ImageView(Static.IMAGE_FAVORITE_TOOLBAR[Controller.getTheme()]));
        historyButton = new ButtonClass(new ImageView(Static.IMAGE_HISTORY_TOOLBAR[Controller.getTheme()]));
        deleteButton = new ButtonClass(new ImageView(Static.IMAGE_DELETE_TOOLBAR[Controller.getTheme()]));

        getItems().addAll(printButton, createPDFButton, updateButton, favoriteButton, historyButton, deleteButton);

        printButton.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDF(true));
        createPDFButton.setOnAction(actionEvent -> TabPaneClass.printOrCreatePDF(false));
        updateButton.setOnAction(actionEvent -> TabPaneClass.updateDataInput());
        favoriteButton.setOnAction(actionEvent -> TabPaneClass.viewFavoriteAction());
        historyButton.setOnAction(actionEvent -> TabPaneClass.viewHistoryAction());
        deleteButton.setOnAction(actionEvent -> TabPaneClass.deleteMedicamentAction());

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        setOnMousePressed(mouseEvent -> requestFocus());
    }

    public void setTheme(int theme) {
        printButton.setGraphic(new ImageView(Static.IMAGE_PRINT_TOOLBAR[theme]));
        createPDFButton.setGraphic(new ImageView(Static.IMAGE_CREATE_PDF_TOOLBAR[theme]));
        updateButton.setGraphic(new ImageView(Static.IMAGE_UPDATE_TOOLBAR[theme]));
        favoriteButton.setGraphic(new ImageView(Static.IMAGE_FAVORITE_TOOLBAR[theme]));
        historyButton.setGraphic(new ImageView(Static.IMAGE_HISTORY_TOOLBAR[theme]));
        deleteButton.setGraphic(new ImageView(Static.IMAGE_DELETE_TOOLBAR[theme]));
    }
}
