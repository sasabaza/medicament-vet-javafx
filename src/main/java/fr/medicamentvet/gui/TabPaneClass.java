package fr.medicamentvet.gui;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.menu.MenuBarClass;
import fr.medicamentvet.gui.simple.TabClass;
import fr.medicamentvet.gui.tabpane.FieldPane;
import fr.medicamentvet.gui.tabpane.FormPane;
import fr.medicamentvet.gui.tabpane.SearchResult;
import fr.medicamentvet.gui.tabpane.TableResult;
import fr.medicamentvet.gui.windows.WindowAbout;
import fr.medicamentvet.gui.windows.WindowDelete;
import fr.medicamentvet.gui.windows.WindowFavorite;
import fr.medicamentvet.gui.windows.WindowHistory;
import fr.medicamentvet.utils.Static;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The purpose of this class is to create components of the TabPane. The class contains methods to perform actions such as creating a PDF file or printing a document, and methods to show About, Delete, Favorite, and History windows.
 */
public class TabPaneClass extends TabPane {

    private static Stage mainStage;
    private static WindowFavorite windowFavorite;
    private static WindowHistory windowHistory;
    private static WindowDelete windowDelete;
    private static WindowAbout windowAbout;
    private static FieldPane fieldPane;
    private static TableResult tableResult;
    private static FormPane formPane;
    private static SearchResult searchResult;
    private static MenuBarClass menu;
    private static ToolBarClass toolBar;
    private static SingleSelectionModel<Tab> selectionModel;

    public TabPaneClass(Stage primaryStage, Application application, MenuBarClass menuBarClass, ToolBarClass toolBarClass) {
        setSide(Side.BOTTOM);

        mainStage = primaryStage;
        menu = menuBarClass;
        toolBar = toolBarClass;

        selectionModel = getSelectionModel();

        tableResult = new TableResult(primaryStage, this);
        fieldPane = new FieldPane(tableResult);

        windowFavorite = new WindowFavorite(primaryStage, Static.FAVORIS_TEXT, true, Static.WINDOW_FAVORITE_STAGE_WIDTH, Static.WINDOW_FAVORITE_STAGE_HEIGHT, fieldPane, selectionModel);
        windowHistory = new WindowHistory(primaryStage, Static.HISTORIQUE_TEXT, true, Static.WINDOW_HISTORY_STAGE_WIDTH, Static.WINDOW_HISTORY_STAGE_HEIGHT, fieldPane, selectionModel);
        windowDelete = new WindowDelete(primaryStage, Static.SUPPRIMER_MEDICAMENT_TEXT, false, Static.WINDOW_DELETE_STAGE_WIDTH, Static.WINDOW_DELETE_STAGE_HEIGHT, tableResult, fieldPane);
        windowAbout = new WindowAbout(primaryStage, application, Static.A_PROPOS_TEXT, false, Static.WINDOW_ABOUT_STAGE_WIDTH, Static.WINDOW_ABOUT_STAGE_HEIGHT);

        VBox fieldPaneAndTableResult = new VBox(fieldPane, tableResult);
        fieldPaneAndTableResult.getStyleClass().add(Static.FIELD_PANE_AND_TABLE_RESULT_CLASS_TEXT);

        searchResult = new SearchResult(primaryStage, fieldPane, selectionModel);
        formPane = new FormPane(primaryStage, searchResult);

        VBox formPaneAndSearchResult = new VBox(formPane, searchResult);

        formPaneAndSearchResult.getStyleClass().add(Static.FORM_PANE_AND_SEARCH_RESULT_CLASS_TEXT);

        TabClass fieldAndTableTab = new TabClass(Static.DONNEES_MEDICAMENT_TEXT, fieldPaneAndTableResult);
        TabClass formAndResultTab = new TabClass(Static.RECHERCHE_AVANCEE_TEXT, formPaneAndSearchResult);

        getTabs().addAll(fieldAndTableTab, formAndResultTab);
    }

    /**
     * The action copies text from the WebView to the clipboard. The action is triggered when user clicks on "Copier" Popup Menu item.
     */
    public static void copyTextAction() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent clipboardContent = new ClipboardContent();

        String text;

        if (selectionModel.isSelected(0)) {
            text = tableResult.getContentSelection();
        } else {
            text = searchResult.getContentSelection();
        }

        clipboardContent.putString(text);
        clipboard.setContent(clipboardContent);
    }

    /**
     * The method creates a PDF file or prints a document from either the Menu, or the Toolbar.
     *
     * @param print Boolean true to print, false to create a PDF file
     */
    public static void printOrCreatePDF(boolean print) {
        if (selectionModel.isSelected(0)) {
            tableResult.generatePDFOrPrint(print);
        } else {
            searchResult.generatePDFOrPrint(print);
        }
    }

    /**
     * The method creates a PDF file or prints a document by clicking on "Imprimer" or "Créer PDF" Popup Menu item.
     *
     * @param print Boolean true to print, false to create a PDF file
     */
    public static void printOrCreatePDFCustomMenu(boolean print) {
        if (selectionModel.isSelected(0)) {
            tableResult.getContextMenuClass().hide();
            tableResult.generatePDFOrPrint(print);
        } else {
            searchResult.getContextMenuClass().hide();
            searchResult.generatePDFOrPrint(print);
        }
    }

    /**
     * The purpose of the method is to set the theme according to the parameter.
     *
     * @param theme Integer selected theme
     */
    public static void setTheme(int theme) {

        Controller.setTheme(theme);
        String string = String.valueOf(theme);
        try {
            Files.write(Static.PATH_FILE_THEME, string.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println(Static.EXCEPTION_TEXT + e.getMessage());
        }

        mainStage.getScene().getStylesheets().setAll(Static.URL_STYLE_SHEET[theme]);
        tableResult.setTheme(theme);
        windowFavorite.setTheme(theme);
        windowHistory.setTheme(theme);
        windowDelete.setTheme(theme);
        windowAbout.setTheme(theme);
        searchResult.setTheme(theme);
        formPane.setTheme(theme);
        menu.setTheme(theme);
        toolBar.setTheme(theme);
    }

    public static void viewFavoriteAction() {
        windowFavorite.showWindow(mainStage);
        windowFavorite.init();
    }

    public static void viewHistoryAction() {
        windowHistory.showWindow(mainStage);
        windowHistory.init();
    }

    public static void viewWindowAboutAction() {
        windowAbout.showWindow(mainStage);
    }

    public static void deleteMedicamentAction() {
        windowDelete.showWindow(mainStage);
        windowDelete.init();
    }

    /**
     * The method sets the data necessary for the proper functioning of the program.
     *
     * @param manual Boolean parameter: true to force the update
     */
    public void setApplicationData(boolean manual) {
        fieldPane.setVariable(manual, windowDelete);
        formPane.setFormPaneModel(manual);
        tableResult.setWindowUpdateVariables(manual);
    }

    /**
     * The method sets all data of the application, updates the autocomplete text field, and the data of the WebView.
     *
     * @param medicament Medicament object
     */
    public void updateData(Medicament medicament) {
        setApplicationData(true);
        fieldPane.setFieldTextAndRetrieveData(medicament);
    }

    /**
     * The method performs an update of the names and ids of the medicaments and the SearchForm object.
     */
    public static void updateDataInput() {
        fieldPane.setVariable(true, windowDelete);
        formPane.setFormPaneModel(true);
    }
}
