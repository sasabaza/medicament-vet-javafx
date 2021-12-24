package fr.medicamentvet.gui.windows.searchform;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.utils.Static;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The objective of the class is to display two autocomplete text fields.
 */
public class WindowSubstanceActive extends WindowClass {

    private final Button buttonOk = getButtonCancel();

    private final Scene scene;
    private final AutocompleteField fieldSubstanceActive1;
    private final AutocompleteField fieldSubstanceActive2;

    public WindowSubstanceActive(Stage primaryStage, String title, boolean resizable, double width, double height) {
        super(primaryStage, title, resizable, width, height);

        buttonOk.setText(Static.OK_TEXT);

        VBox vBox = new VBox();

        fieldSubstanceActive1 = new AutocompleteField(vBox, Static.SUBSTANCE_ACTIVE_1_TEXT, Static.SUBSTANCE_ACTIVE_1_TEXT);
        fieldSubstanceActive2 = new AutocompleteField(vBox, Static.SUBSTANCE_ACTIVE_2_TEXT, Static.SUBSTANCE_ACTIVE_2_TEXT);

        HBox hBox = new HBox(buttonOk);
        vBox.getChildren().addAll(fieldSubstanceActive1, fieldSubstanceActive2, hBox);

        scene = new Scene(vBox);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBox.getStyleClass().add(Static.WINDOW_SUBSTANCE_ACTIVE_CLASS_TEXT);

        getStage().setScene(scene);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scene.setOnMousePressed(mouseEvent -> vBox.requestFocus());
    }

    public void init() {
        buttonOk.requestFocus();
    }

    public AutocompleteField getFieldSubstanceActive1() {
        return fieldSubstanceActive1;
    }

    public AutocompleteField getFieldSubstanceActive2() {
        return fieldSubstanceActive2;
    }

    public void setArrays(String[] arrayString) {
        fieldSubstanceActive1.setArrayString(arrayString);
        fieldSubstanceActive2.setArrayString(arrayString);
    }

    public void setFieldsDisable(boolean enable) {
        fieldSubstanceActive1.setDisable(enable);
        fieldSubstanceActive2.setDisable(enable);
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }
}
