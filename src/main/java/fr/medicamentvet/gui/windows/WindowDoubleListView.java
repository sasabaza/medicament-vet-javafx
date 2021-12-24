package fr.medicamentvet.gui.windows;

import fr.medicamentvet.gui.simple.ListViewClass;
import fr.medicamentvet.utils.Static;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * The goal of this class is to create two ListViews, two buttons and the actions attached to the ListViews and the buttons.
 */
public class WindowDoubleListView extends WindowClass {

    private final ListViewClass listView;
    private final ListViewClass listViewSelection;
    public final Label labelChoice;
    public final Label labelSelection;
    private final Button buttonAdd;
    private final Button buttonRemove;

    private ObservableList<String> observableList;
    private ObservableList<String> observableListSelection;

    /**
     * Maximum number of elements added to the ListView
     */
    private final int maxCount;

    public WindowDoubleListView(Stage primaryStage, String title, boolean resizable, double width, double height, int maxCount) {
        super(primaryStage, title, resizable, width, height);

        this.maxCount = maxCount;

        observableList = FXCollections.observableArrayList();
        listView = new ListViewClass(SelectionMode.MULTIPLE, Static.CELL_SIZE, observableList);

        observableListSelection = FXCollections.observableArrayList();
        listViewSelection = new ListViewClass(SelectionMode.MULTIPLE, Static.CELL_SIZE, observableListSelection);

        labelChoice = new Label(Static.CHOIX_POSSIBLES_TEXT);
        labelSelection = new Label(Static.SELECTION_TEXT);

        buttonAdd = new Button(Static.BUTTON_ARROW_DOWN_AJOUTER_TEXT);
        buttonRemove = new Button(Static.BUTTON_ARROW_UP_SUPPRIMER_TEXT);

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                addAction();
            }
        });

        listView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        listViewSelection.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() >= Static.CLICK_NUMBER_TWO) {
                removeAction();
            }
        });

        listViewSelection.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        buttonAdd.setOnAction(actionEvent -> addAction());

        buttonRemove.setOnAction(actionEvent -> removeAction());
    }

    public void setObservableList(ObservableList<String> observableList) {
        this.observableList = observableList;
    }

    public ObservableList<String> getObservableListSelection() {
        return observableListSelection;
    }

    public void setObservableListSelection(ObservableList<String> observableListSelection) {
        this.observableListSelection = observableListSelection;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public ListView<String> getListViewSelection() {
        return listViewSelection;
    }

    public Label getLabelChoice() {
        return labelChoice;
    }

    public Label getLabelSelection() {
        return labelSelection;
    }

    public Button getButtonAdd() {
        return buttonAdd;
    }

    public Button getButtonRemove() {
        return buttonRemove;
    }

    /**
     * The method sets the items of the ListViews.
     *
     * @param observableList          List of Strings
     * @param observableListSelection List of selected Strings
     */
    public void setModels(ObservableList<String> observableList, ObservableList<String> observableListSelection) {
        listView.setItems(observableList);
        setObservableList(observableList);

        listViewSelection.setItems(observableListSelection);
        setObservableListSelection(observableListSelection);
    }

    public void setModel(ObservableList<String> observableList) {
        listView.setItems(observableList);
        setObservableList(observableList);
    }

    private void addAction() {
    }

    private void removeAction() {
    }
}
