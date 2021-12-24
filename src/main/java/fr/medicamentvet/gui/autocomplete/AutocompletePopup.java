package fr.medicamentvet.gui.autocomplete;

import fr.medicamentvet.utils.Static;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

/**
 * This class is the autocomplete popup component. It contains methods to show the popup, to track the mouse click and movement, to track the keystroke attached to the suggestions list.
 */
public class AutocompletePopup extends Popup {

    private final Node container;
    private final ListView<String> suggestions;
    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    private AutocompleteField autocompleField;
    private String[] arrayString;

    public AutocompletePopup(Node container) {

        this.container = container;

        suggestions = new ListView<>(observableList);
        suggestions.setFixedCellSize(Static.CELL_SIZE);

        suggestions.getStyleClass().add(Static.AUTOCOMPLETE_POPUP_CLASS_TEXT);

        getContent().add(suggestions);

        suggestions.setOnMousePressed(this::mousePressedAction);
        suggestions.setOnMouseEntered(this::mouseEnteredOrMoved);
        suggestions.setOnMouseMoved(this::mouseEnteredOrMoved);

        suggestions.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                actionDownKey();
            }

            if (keyEvent.getCode() == KeyCode.UP) {
                actionUpKey();
            }

            if (keyEvent.getCode() == KeyCode.ENTER) {
                actionEnterKey();
            }

            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                noSuggestions();
            }
        });
    }

    public void setVariables(AutocompleteField autocompleteField, String[] arrayString) {
        this.autocompleField = autocompleteField;
        this.arrayString = arrayString;
    }

    /**
     * The method gets the selected text from the suggestions list and processes the action.
     *
     * @param mouseEvent Event for mouse pressed
     */
    private void mousePressedAction(MouseEvent mouseEvent) {
    }

    private void mouseEnteredOrMoved(MouseEvent mouseEvent) {
    }

    private void noSuggestions() {
    }

    /**
     * The method is triggered when user presses the Enter key and then sends the selected text.
     */
    private void actionEnterKey() {
    }

    /**
     * The method is activated when there is a Down arrow keystroke. The method selects the specific item of the list and sets the text of the autocomplete field.
     */
    private void actionDownKey() {
    }

    /**
     * This method is initiated when there is a Up arrow keystroke. The method selects the specific item of the list and sets the text of the autocomplete field.
     */
    private void actionUpKey() {
    }
}
