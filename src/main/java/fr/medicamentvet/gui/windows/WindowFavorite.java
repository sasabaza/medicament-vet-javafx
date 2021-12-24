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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 * The class represents the Favorite window: it shows user's selection of names of the medicaments.
 */
public class WindowFavorite extends WindowClass {

    private final Button buttonOk = getButtonCancel();
    private final WebEngine webEngine;

    private final Scene scene;
    private int skip;

    public WindowFavorite(Stage primaryStage, String title, boolean resizable, double width, double height, FieldPane fieldPane, SingleSelectionModel<Tab> selectionModel) {
        super(primaryStage, title, resizable, width, height);

        buttonOk.setText(Static.OK_TEXT);

        WebView webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[Controller.getTheme()]);

        VBox webViewBox = new VBox(webView);
        webViewBox.getStyleClass().add(Static.WEBVIEW_BOX_CLASS_TEXT);

        GridPane gridPane = new GridPane();

        GridPane.setConstraints(webViewBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.ALWAYS);

        GridPane.setConstraints(buttonOk, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER);

        gridPane.getChildren().addAll(webViewBox, buttonOk);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_FAVORITE_CLASS_TEXT);

        getStage().setScene(scene);

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
            window.setMember(Static.WINDOW_FAVORITE_CLASS_TEXT, this);
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

        String data = favoriteData(skip);

        if (!data.isEmpty()) {
            data = data.replace(Static.BACKSLASH_TEXT, Static.DOUBLE_BACKSLASH_TEXT);
            String insert = Static.INSERT_JS_FAVORITE_FIRST_PART + (skip - 1) + Static.INSERT_JS_SECOND_PART + data + Static.INSERT_JS_END_PART;
            webEngine.executeScript(insert);
        }
    }

    public void init() {
        skip = 0;

        String data = favoriteData(skip);
        webEngine.loadContent(data);
        buttonOk.requestFocus();
    }

    public void setTheme(int theme) {
        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[theme]);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }

    /**
     * The purpose of the method is to get the favorite list.
     *
     * @param skip Number of values to skip
     * @return HTML content
     */
    private String favoriteData(int skip) {
        return "";
    }
}
