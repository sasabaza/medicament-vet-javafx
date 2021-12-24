package fr.medicamentvet.gui.windows;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.simple.ButtonClass;
import fr.medicamentvet.utils.Static;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * The purpose of this class is to set the base properties of the Window component.
 */
public class WindowClass extends Window {

    private final ButtonClass buttonOk;
    private final Button buttonCancel;

    private Stage stage;
    private Medicament medicament;

    public WindowClass(Stage primaryStage, String title, boolean resizable, double width, double height) {
        if (Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT) && Static.OS_DESKTOP_UI.equals(Static.OS_UI_GNOME_TEXT)) {
            stage = new Stage();
        }
        if (Static.OS_NAME.equals(Static.OS_NAME_WINDOWS_10_TEXT)) {
            stage = new Stage(StageStyle.UTILITY);
        }

        stage.setTitle(title);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);

        if (!resizable) {
            stage.setResizable(false);
            stage.setMaxWidth(width);
            stage.setMaxHeight(height);
        }

        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.setWidth(width);
        stage.setHeight(height);

        buttonOk = new ButtonClass(Static.OK_TEXT);
        buttonCancel = new Button();

        buttonCancel.setOnAction(actionEvent -> stage.hide());

        stage.setOnCloseRequest(actionEvent -> {

            if (!stage.isFocused() && Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT) && Static.OS_DESKTOP_UI.equals(Static.OS_UI_GNOME_TEXT)) {
                actionEvent.consume();
                stage.toFront();
            }
        });
    }

    public Button getButtonOk() {
        return buttonOk;
    }

    public Button getButtonCancel() {
        return buttonCancel;
    }

    public Stage getStage() {
        return stage;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public void hideWindow() {
        stage.hide();
    }

    public void showWindow(Stage primaryStage) {
        double x = primaryStage.getX() + (primaryStage.getWidth() - stage.getWidth()) / 2;
        double y = primaryStage.getY() + (primaryStage.getHeight() - stage.getHeight()) / 2;

        stage.setX(x);
        stage.setY(y);

        stage.show();

        primaryStage.toBack();
        stage.toFront();
    }
}
