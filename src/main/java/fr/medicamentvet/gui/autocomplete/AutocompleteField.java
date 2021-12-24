package fr.medicamentvet.gui.autocomplete;

import fr.medicamentvet.utils.Static;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextField;

/**
 * The class represents the autocomplete text field component.
 */
public class AutocompleteField extends TextField {

    private final AutocompletePopup autocompletePopup;

    private boolean keyStrokeDownUp = false;
    private String[] arrayString;

    public AutocompleteField(Node container, String name, String promptText) {
        setId(name);
        setPromptText(promptText);
        setContextMenu(new ContextMenu());

        autocompletePopup = new AutocompletePopup(container);

        textProperty().addListener((observableValue, oldString, newString) -> updatePopup(false));

        setOnMousePressed(mouseEvent -> updatePopup(true));

        /*
        The listener checks whether the autocomplete field is focused or not: hide the autocomplete pop up when the field is no longer focused.
         */
        focusedProperty().addListener((observableValue, isNotFocused, isFocused) -> {
            if (isFocused) {
                getStyleClass().removeAll(Static.ERROR_FIELD_CLASS_TEXT);
            } else {
                if (autocompletePopup.isShowing()) {
                    autocompletePopup.hide();
                }
            }
        });
    }

    public void setAutocompleteFieldText(Object nom) {

        // When user clicks on a name from Favorite window for example, we want to set the new text of the autocomplete field.
        // We initialize the boolean keyStrokeDownUp to true so that the popup does not show up.
        this.keyStrokeDownUp = true;

        setText(String.valueOf(nom));

        // Then we initialize the boolean keyStrokeDownUp to the initial value (false).
        this.keyStrokeDownUp = false;
    }

    public String[] getArrayString() {
        return arrayString;
    }

    public void setArrayString(String[] arrayString) {
        this.arrayString = arrayString;

        // We need the autocomplete field component to set the text but also to get the width in AutocompletePopup class.
        autocompletePopup.setVariables(this, arrayString);
    }

    /**
     * The final purpose of the method is to show a popup holding a list of suggestions based on user text input.
     *
     * @param click Boolean user click inside the autocomplete field
     */
    private void updatePopup(boolean click) {
    }
}
