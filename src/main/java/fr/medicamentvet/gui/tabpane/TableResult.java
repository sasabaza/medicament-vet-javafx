package fr.medicamentvet.gui.tabpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.windows.update.WindowUpdate;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import fr.medicamentvet.utils.Utils;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The class displays the information about the medicament, and contains three buttons:<br>
 * A button to edit the information<br>
 * A toggle button to view the base or Rcp information<br>
 * A button to add the medicament name to the favorite list
 */
public class TableResult extends VBox {

    private static Integer medicamentId;

    private final ContextMenuClass contextMenuClass = new ContextMenuClass();
    private final WindowUpdate windowUpdate;
    private final VBox webViewBox;
    private final WebView webView;
    private final WebEngine webEngine;
    private final Stage primaryStage;

    private String[] arrayMedicamentData;

    public TableResult(Stage primaryStage, TabPaneClass tabPaneClass) {

        this.primaryStage = primaryStage;

        Static.UPDATE_BUTTON.setDisable(true);
        Static.RCP_TOGGLE_BUTTON.setDisable(true);
        Static.FAVORITE_TOGGLE_BUTTON.setDisable(true);

        windowUpdate = new WindowUpdate(primaryStage, Static.WINDOW_TITLE_UPDATE, false, Static.WINDOW_UPDATE_WIDTH, Static.WINDOW_UPDATE_HEIGHT, tabPaneClass);

        GridPane gridPane = new GridPane();

        GridPane.setConstraints(Static.UPDATE_BUTTON, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));
        GridPane.setConstraints(Static.RCP_TOGGLE_BUTTON, Static.COLUMN_INDEX_ONE, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_TWENTY, Static.INSET_TEN, Static.INSET_ZERO));
        GridPane.setConstraints(Static.FAVORITE_TOGGLE_BUTTON, Static.COLUMN_INDEX_TWO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.NEVER, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        gridPane.getChildren().addAll(Static.UPDATE_BUTTON, Static.RCP_TOGGLE_BUTTON, Static.FAVORITE_TOGGLE_BUTTON);

        webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[Controller.getTheme()]);

        webViewBox = new VBox(webView);
        webViewBox.getStyleClass().add(Static.WEBVIEW_BOX_CLASS_TEXT);

        getChildren().addAll(gridPane, webViewBox);

        Static.UPDATE_BUTTON.setOnAction(actionEvent -> {
            if (arrayMedicamentData != null) {
                Medicament medicament = Controller.getMedicament();
                windowUpdate.showWindow(primaryStage);
                windowUpdate.init(medicament);
            }
        });

        webView.setOnMousePressed(mouseEvent -> {
            webView.requestFocus();
            if (MouseButton.SECONDARY == mouseEvent.getButton()) {
                contextMenuClass.show(webView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            } else {
                contextMenuClass.hide();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        webView.setOnScroll(scrollEvent -> webView.requestFocus());

        Static.RCP_TOGGLE_BUTTON.setOnAction(actionEvent -> {
            if (arrayMedicamentData != null) {
                if (Static.RCP_TOGGLE_BUTTON.isSelected()) {
                    webEngine.loadContent(arrayMedicamentData[1]);
                } else {
                    webEngine.loadContent(arrayMedicamentData[0]);
                }
            }
        });

        Static.FAVORITE_TOGGLE_BUTTON.setOnAction(actionEvent -> {
            if (arrayMedicamentData != null) {

                if (Static.FAVORITE_TOGGLE_BUTTON.isSelected()) {
                    addFavorite(medicamentId);
                } else {
                    removeFavorite(medicamentId);
                }
            }
        });
    }

    public ContextMenuClass getContextMenuClass() {
        return contextMenuClass;
    }

    public void setArrayMedicamentData(String[] arrayMedicamentData) {
        this.arrayMedicamentData = arrayMedicamentData;
    }

    public void generatePDFOrPrint(boolean print) {

        if (Static.RCP_TOGGLE_BUTTON.isSelected()) {
            generatePDFOrPrintRcp(print);
        } else {
            generatePDFOrPrintMedicament(print);
        }
    }

    /**
     * The method gets the information about the Medicament object given the id. The process is done in a background thread.
     *
     * @param id                id of the Medicament object
     * @param autocompleteField AutocompleteField
     */
    public void setEditorPaneContentById(int id, AutocompleteField autocompleteField) {
        medicamentId = id;

        if (id != -1) {
            setElements();

            Timeline timeline = Utils.waitAnimation(webView);
            webViewBox.setBackground(Static.WEBVIEW_BOX_LIGHT_BACKGROUNDS[Controller.getTheme()]);
            timeline.play();

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);

            TaskProducer taskProducer = new TaskProducer(Static.TABLE_RESULT_CLASS_TEXT, autocompleteField, id, webEngine, webViewBox, timeline, Static.UPDATE_BUTTON, Static.RCP_TOGGLE_BUTTON, Static.FAVORITE_TOGGLE_BUTTON, this);
            new Thread(taskProducer).start();
        } else {
            clearEditorPane(Static.AUCUNES_DONNEES);

            autocompleteField.setDisable(false);
        }
    }

    public void setEditorPaneContent(Medicament medicament) {

        int id = medicament.getId();
        medicamentId = id;

        setElements();

        arrayMedicamentData = Controller.createArray(medicament);
        setContent(arrayMedicamentData, id);
    }

    /**
     * The method clears the WebView with custom HTML content, disables the buttons, and changes the state of the rcpToggleButton and the favoriteToggleButton.
     *
     * @param text HTML content
     */
    public void clearEditorPane(String text) {
        webEngine.loadContent(text);

        Static.UPDATE_BUTTON.setDisable(true);
        Static.RCP_TOGGLE_BUTTON.setDisable(true);
        Static.FAVORITE_TOGGLE_BUTTON.setDisable(true);

        Static.RCP_TOGGLE_BUTTON.setSelected(false);
        Static.FAVORITE_TOGGLE_BUTTON.setSelected(false);
    }

    public String getContentSelection() {
        return (String) webEngine.executeScript(Static.WINDOW_GET_SELECTION_TOSTRING);
    }

    public void setWindowUpdateVariables(boolean manual) {
        windowUpdate.setVariables(manual);
    }

    public void setTheme(int theme) {
        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[theme]);
        windowUpdate.setTheme(theme);
        contextMenuClass.setTheme(theme);
        setThemeContent();
    }

    private void setThemeContent() {
    }

    /**
     * The method sets the content of the WebEngine to an empty HTML body and disables the buttons.
     */
    private void setElements() {
    }

    /**
     * The method sets the content of the WebEngine with the information about the medicament and checks whether true or false the medicament is in the favorite list.
     *
     * @param array Array of Strings: the first element contains the base information about the medicament
     * @param id    id of the Medicament object
     */
    private void setContent(String[] array, int id) {
    }

    /**
     * The method adds a new entry to the favorite file.
     *
     * @param id id of the Medicament object
     */
    private void addFavorite(Integer id) {
    }

    /**
     * The method removes a new entry from the favorite file.
     *
     * @param id id of the Medicament object
     */
    private void removeFavorite(Integer id) {
    }

    /**
     * The method either performs a printing or generates a PDF file of the base information about the medicament.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    private void generatePDFOrPrintMedicament(boolean print) {
    }

    /**
     * The method either performs a printing or generates a PDF file of the Rcp.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    private void generatePDFOrPrintRcp(boolean print) {
    }
}
