package fr.medicamentvet;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.MainPane;
import fr.medicamentvet.utils.Static;
import java.util.Locale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class represents the entry point of the program. The class creates and styles the scene of the main window.
 */
public class ApplicationClass extends Application {

    @Override
    public void start(Stage primaryStage) {

        Locale.setDefault(Locale.FRANCE);

        primaryStage.setTitle(Static.TITLE_APPLICATION);
        primaryStage.setWidth(Static.STAGE_WIDTH);
        primaryStage.setHeight(Static.STAGE_HEIGHT);
        primaryStage.setMinWidth(Static.STAGE_WIDTH);
        primaryStage.setMinHeight(Static.STAGE_HEIGHT);

        createDirectoryAndFiles();

        Scene scene = new Scene(new MainPane(primaryStage, this));

        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET[Controller.getTheme()]);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * The method creates a directory and empty files. Files are needed to save and retrieve the history and the favorites.
     */
    private void createDirectoryAndFiles() {
    }
}
