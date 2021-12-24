package fr.medicamentvet.gui.windows;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.tabpane.FieldPane;
import fr.medicamentvet.utils.Static;
import java.util.Map;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * The main purpose of the class is to show a window containing a list of previous names searched by the user.
 */
public class WindowHistory extends WindowClass {

    private final Button buttonOk = getButtonCancel();
    private final WebEngine webEngine;

    private final Scene scene;
    private int skip;

    public WindowHistory(Stage primaryStage, String title, boolean resizable, double width, double height, FieldPane fieldPane, SingleSelectionModel<Tab> selectionModel) {
        super(primaryStage, title, resizable, width, height);

        Button buttonDelete = getButtonOk();
        buttonDelete.getStyleClass().add(Static.BUTTON_HISTORY_CLASS_TEXT);

        buttonDelete.setText(Static.BUTTON_SUPPRIMER_HISTORIQUE_TEXT);
        buttonOk.setText(Static.OK_TEXT);

        HBox hBox = new HBox(buttonDelete, buttonOk);

        WebView webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[Controller.getTheme()]);

        VBox webViewBox = new VBox(webView);
        webViewBox.getStyleClass().add(Static.WEBVIEW_BOX_CLASS_TEXT);

        GridPane gridPane = new GridPane();

        GridPane.setConstraints(webViewBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);

        GridPane.setConstraints(hBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER);

        gridPane.getChildren().addAll(webViewBox, hBox);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_HISTORY_CLASS_TEXT);

        getStage().setScene(scene);

        buttonDelete.setOnAction(actionEvent -> {
            deleteHistoryData();
            webEngine.loadContent(Static.EMPTY_BODY_HTML);
        });

        webEngine.setOnAlert(stringWebEvent -> {
            int id = Integer.parseInt(stringWebEvent.getData());

            Map<Integer, String> idNomMap = Controller.getIdNomMap();
            String nom = idNomMap.get(id);

            selectionModel.select(Static.DONNEES_MEDICAMENT_TAB_INDEX);
            fieldPane.setFieldTextByNom(nom);
            hideWindow();
        });

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldValue, newValue) -> {
//            if (newValue != Worker.State.SUCCEEDED) {return;}

            JSObject window = (JSObject) webEngine.executeScript(Static.WINDOW_TEXT);
            window.setMember(Static.WINDOW_HISTORY_CLASS_TEXT, this);
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * The purpose of the method is to insert HTML content to the WebView. Whenever user scrolls down new HTML content might be inserted.
     */
    public void insertHTML() {
        skip = skip + Static.LIMIT_ENTRY_NUMBER;
        String data = historyData(skip);

        if (!data.isEmpty()) {
            data = data.replace(Static.BACKSLASH_TEXT, Static.DOUBLE_BACKSLASH_TEXT);
            String insert = Static.INSERT_JS_HISTORY_FIRST_PART + (skip - 1) + Static.INSERT_JS_SECOND_PART + data + Static.INSERT_JS_END_PART;
            webEngine.executeScript(insert);
        }
    }

    public void init() {
        skip = 0;

        String data = historyData(skip);
        webEngine.loadContent(data);
        buttonOk.requestFocus();
    }

    public void setTheme(int theme) {
        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[theme]);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }

    /**
     * The aim of the method is to get the history of queried names that will be added to the WebView.
     *
     * @param skip Number of values to skip
     * @return HTML content
     */
    private String historyData(int skip) {
        return "";
    }

    private void deleteHistoryData() {
    }
}
