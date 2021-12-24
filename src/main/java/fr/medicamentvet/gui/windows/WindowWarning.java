package fr.medicamentvet.gui.windows;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.simple.TextClass;
import fr.medicamentvet.utils.Static;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 * This class shows a message in a window to inform user whenever there is an error or a warning.
 */
public class WindowWarning extends Window {

    private final Scene scene;
    private final HBox hBoxContent;
    private final Stage primaryStage;

    private Stage stage;

    public WindowWarning(Stage primaryStage, String title, String message) {

        this.primaryStage = primaryStage;

        if (Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT) && Static.OS_DESKTOP_UI.equals(Static.OS_UI_GNOME_TEXT)) {
            stage = new Stage();
        }
        if (Static.OS_NAME.equals(Static.OS_NAME_WINDOWS_10_TEXT)) {
            stage = new Stage(StageStyle.UTILITY);
        }

        stage.setTitle(title);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        stage.setMinWidth(Static.WINDOW_WARNING_WIDTH);
        stage.setMinHeight(Static.WINDOW_WARNING_HEIGHT);
        stage.setWidth(Static.WINDOW_WARNING_WIDTH);
        stage.setHeight(Static.WINDOW_WARNING_HEIGHT);

        TextClass textClass = new TextClass(message, Static.CONTENT_CLASS_TEXT);
        textClass.setWrappingWidth(Static.WINDOW_WARNING_WIDTH - Static.MARGIN_WARNING_WIDTH);
        textClass.setLineSpacing(Static.TEXT_LINE_SPACING);

        Static.H_BOX_IMAGE_VIEW_WARNING.getStyleClass().add(Static.HBOX_WARNING_IMAGE_CLASS_TEXT);
        hBoxContent = new HBox(Static.H_BOX_IMAGE_VIEW_WARNING, textClass);
        hBoxContent.getStyleClass().add(Static.HBOX_CONTENT_CLASS_TEXT);

        HBox hBoxBottom = new HBox(Static.BUTTON_OK);
        hBoxBottom.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        VBox root = new VBox(hBoxContent, hBoxBottom);

        scene = new Scene(root);

        root.getStyleClass().add(Static.WINDOW_WARNING_CLASS_TEXT);

        stage.setScene(scene);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                stage.hide();
            }
        });

        Static.BUTTON_OK.setOnAction(actionEvent -> stage.hide());

        stage.setOnCloseRequest(actionEvent -> {

            if (!stage.isFocused() && Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT) && Static.OS_DESKTOP_UI.equals(Static.OS_UI_GNOME_TEXT)) {
                actionEvent.consume();
                stage.toFront();
            }
        });
    }

    public void showWindow() {
        double x = primaryStage.getX() + (primaryStage.getWidth() - stage.getWidth()) / 2;
        double y = primaryStage.getY() + (primaryStage.getHeight() - stage.getHeight()) / 2;

        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        stage.setX(x);
        stage.setY(y);

        stage.show();

        if (hBoxContent.getHeight() > Static.HBOX_CONTENT_MINIMUM_HEIGHT) {
            stage.setHeight(Static.WINDOW_WARNING_HEIGHT + hBoxContent.getHeight() - Static.HBOX_CONTENT_MINIMUM_HEIGHT);
        }

        stage.toFront();
    }
}
