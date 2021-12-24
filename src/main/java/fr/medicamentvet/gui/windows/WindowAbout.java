package fr.medicamentvet.gui.windows;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.simple.TextClass;
import fr.medicamentvet.utils.Static;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class shows information about the application in a Window.
 */
public class WindowAbout extends WindowClass {

    private final Scene scene;

    public WindowAbout(Stage primaryStage, Application application, String title, boolean resizable, double width, double height) {
        super(primaryStage, title, resizable, width, height);

        TextClass dates = new TextClass(Static.DATES_TEXT, Static.CONTENT_CLASS_TEXT);
        Hyperlink hyperlink = new Hyperlink(Static.HYPERLINK_TEXT);
        VBox vBox = new VBox(hyperlink, dates);
        vBox.getStyleClass().add(Static.VBOX_BOTTOM_CLASS_TEXT);

        TextClass name = new TextClass(Static.TITLE_TEXT, Static.CONTENT_CLASS_TEXT);
        TextClass version = new TextClass(Static.VERSION_TEXT, Static.CONTENT_CLASS_TEXT);
        VBox root = new VBox(name, version, vBox);

        scene = new Scene(root);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        root.getStyleClass().add(Static.WINDOW_ABOUT_CLASS_TEXT);

        getStage().setScene(scene);

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        hyperlink.setOnAction(event -> application.getHostServices().showDocument(Static.URL));
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }
}
