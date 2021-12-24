package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.windows.WindowDoubleListViewVertical;
import fr.medicamentvet.utils.Static;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * The class lets user select one or multiple Excipient QSP items from the ListView.
 */
public class WindowExcipientQSP extends WindowDoubleListViewVertical {

    private final Scene scene;
    private final Stage primaryStage;
    private final WindowComposition windowComposition;
    private final Button buttonNext;

    public WindowExcipientQSP(Stage primaryStage, WindowConditionDelivrance windowConditionDelivrance, String title, boolean resizable, double width, double height, int maxCount, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height, maxCount);

        this.primaryStage = primaryStage;

        windowComposition = new WindowComposition(primaryStage, this, Static.WINDOW_COMPOSITION_TITRE, false, Static.WINDOW_COMPOSITION_WIDTH, Static.WINDOW_COMPOSITION_HEIGHT, Static.COMPOSITION_MAXIMUM_NUMBER, tabPaneClass);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBox = new HBox(buttonReturn, buttonNext);

        GridPane gridPane = getGridPane();

        GridPane.setConstraints(hBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER);

        gridPane.getChildren().add(hBox);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_EXCIPIENT_QSP_CLASS_TEXT);

        getStage().setScene(scene);

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        buttonReturn.setOnAction(actionEvent -> {
            Medicament medicament = getMedicament();
            medicament.setExcipientQSPList(getObservableListSelection());

            hideWindow();
            windowConditionDelivrance.showWindow(primaryStage);
            windowConditionDelivrance.init(medicament);
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * The method sets the model of the ListViews.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        List<String> list = medicament.getExcipientQSPList();
        if (list == null) {
            list = new ArrayList<>();
        }

        List<String> excipientQSPList = Controller.getUpdateForm().getExcipientQSPList();
        excipientQSPList.removeAll(list);

        setModels(FXCollections.observableArrayList(excipientQSPList), FXCollections.observableArrayList(list));
        buttonNext.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowComposition.setTheme(theme);
    }

    /**
     * The method makes an update of the Medicament object by setting the list of selected items and displays the next window.
     */
    private void buttonNextAction() {
    }
}
