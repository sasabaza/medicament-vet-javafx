package fr.medicamentvet.gui.windows.searchform;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.windows.WindowDoubleListViewVertical;
import fr.medicamentvet.utils.Static;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * The class lets user select one or multiple Espèce items from the ListView.
 */
public class WindowEspece extends WindowDoubleListViewVertical {

    private final Button buttonOk = getButtonCancel();

    private final Scene scene;

    public WindowEspece(Stage primaryStage, String title, boolean resizable, double width, double height, int maxCount) {
        super(primaryStage, title, resizable, width, height, maxCount);

        buttonOk.setText(Static.OK_TEXT);

        GridPane gridPane = getGridPane();

        GridPane.setConstraints(buttonOk, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER);

        gridPane.getChildren().add(buttonOk);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_ESPECE_CLASS_TEXT);

        getStage().setScene(scene);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    public void init() {
        buttonOk.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }
}
