package fr.medicamentvet.gui.windows;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.tabpane.FieldPane;
import fr.medicamentvet.gui.tabpane.TableResult;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class shows a window that allows user to delete a medicament.
 */
public class WindowDelete extends WindowClass {

    private final Scene scene;
    private final Button buttonClose = getButtonCancel();
    private final AutocompleteField fieldDelete;

    public WindowDelete(Stage primaryStage, String title, boolean resizable, double width, double height, TableResult tableResult, FieldPane fieldPane) {
        super(primaryStage, title, resizable, width, height);

        Button buttonDelete = getButtonOk();
        buttonDelete.setText(Static.BUTTON_SUPPRIMER_TEXT);

        buttonClose.setText(Static.BUTTON_FERMER_TEXT);

        VBox vBox = new VBox();

        fieldDelete = new AutocompleteField(vBox, Static.NOM_DU_MEDICAMENT_TEXT, Static.NOM_DU_MEDICAMENT_TEXT);

        HBox hBox = new HBox(buttonDelete, buttonClose);
        vBox.getChildren().addAll(fieldDelete, hBox);

        scene = new Scene(vBox);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBox.getStyleClass().add(Static.WINDOW_DELETE_CLASS_TEXT);

        getStage().setScene(scene);

        buttonDelete.setOnAction(actionEvent -> {

            String text = fieldDelete.getText();

            if (text != null && !text.isEmpty()) {

                String nom = text.toUpperCase().trim();
                Map<String, Integer> nomIdMap = Controller.getNomIdMap();

                if (nomIdMap != null) {
                    boolean contain = nomIdMap.containsKey(nom);

                    if (contain) {
                        buttonDelete.setDisable(true);

                        int id = nomIdMap.get(nom);

                        StatusBar.setLabelMessage(Static.EMPTY_TEXT);

                        // Deletion action is done in a background thread with the help of TaskProducer class.
                        TaskProducer taskProducer = new TaskProducer(Static.WINDOW_DELETE_CLASS_TEXT, buttonDelete, id, tableResult, fieldPane);
                        new Thread(taskProducer).start();
                    } else {
                        WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_DELETE_TEXT, Static.WINDOW_WARNING_MESSAGE_TEXT);
                        windowWarning.showWindow();
                    }
                }
            }
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scene.setOnMousePressed(mouseEvent -> vBox.requestFocus());
    }

    public void init() {
        fieldDelete.setText(Static.EMPTY_TEXT);
        buttonClose.requestFocus();
    }

    public void setVariable(String[] arrayString) {
        fieldDelete.setArrayString(arrayString);
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }
}
