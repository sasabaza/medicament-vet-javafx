package fr.medicamentvet.gui.windows.searchform;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.simple.DatePickerClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.utils.Static;
import java.time.LocalDate;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * The class exposes to the user beginning and end dates inputs that can be edited.
 */
public class WindowDate extends WindowClass {

    private final Button buttonOk = getButtonCancel();

    private final DatePickerClass datePickerStartDate = new DatePickerClass(LocalDate.of(Static.DATE_BEGIN_YEAR, Static.DATE_BEGIN_MONTH_ONE, Static.DATE_BEGIN_DAY_ONE), Static.DATE_DE_DEBUT_TEXT);
    private final DatePickerClass datePickerEndDate = new DatePickerClass(LocalDate.now(), Static.DATE_DE_FIN_TEXT);

    private final Scene scene;

    public WindowDate(Stage primaryStage, String title, boolean resizable, double width, double height) {
        super(primaryStage, title, resizable, width, height);

        buttonOk.setText(Static.OK_TEXT);

        GridPane gridPane = new GridPane();

        GridPane.setConstraints(Static.LABEL_START_DATE, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));

        GridPane.setConstraints(datePickerStartDate, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_ZERO));

        GridPane.setConstraints(Static.LABEL_END_DATE, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));

        GridPane.setConstraints(datePickerEndDate, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_THREE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_ZERO));

        GridPane.setConstraints(buttonOk, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FOUR, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER);

        gridPane.getChildren().addAll(Static.LABEL_START_DATE, datePickerStartDate, Static.LABEL_END_DATE, datePickerEndDate, buttonOk);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_DATE_CLASS_TEXT);

        getStage().setScene(scene);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    public DatePickerClass getDatePickerStartDate() {
        return datePickerStartDate;
    }

    public DatePickerClass getDatePickerEndDate() {
        return datePickerEndDate;
    }

    public void init() {
        buttonOk.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }
}
