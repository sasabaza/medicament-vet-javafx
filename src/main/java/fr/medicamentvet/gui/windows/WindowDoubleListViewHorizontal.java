package fr.medicamentvet.gui.windows;

import fr.medicamentvet.utils.Static;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * The class puts the ListViews in a separate row so that they are aligned horizontally.
 */
public class WindowDoubleListViewHorizontal extends WindowDoubleListView {

    private final GridPane gridPane;

    public WindowDoubleListViewHorizontal(Stage primaryStage, String title, boolean resizable, double width, double height, int maxCount) {
        super(primaryStage, title, resizable, width, height, maxCount);

        ListView<String> listView = getListView();
        ListView<String> listViewSelection = getListViewSelection();

        Label labelChoice = getLabelChoice();
        Label labelSelection = getLabelSelection();

        Button buttonAdd = getButtonAdd();
        Button buttonRemove = getButtonRemove();

        HBox hBox = new HBox(buttonAdd, buttonRemove);
        hBox.getStyleClass().add(Static.WINDOW_DOUBLE_LIST_VIEW_HORIZONTAL_CLASS_TEXT);

        gridPane = new GridPane();

        GridPane.setConstraints(labelChoice, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));

        GridPane.setConstraints(listView, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        GridPane.setConstraints(hBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        GridPane.setConstraints(labelSelection, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_THREE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));

        GridPane.setConstraints(listViewSelection, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FOUR, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        gridPane.getChildren().addAll(labelChoice, listView, hBox, labelSelection, listViewSelection);
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
