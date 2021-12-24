package fr.medicamentvet.gui;

import fr.medicamentvet.gui.menu.MenuBarClass;
import fr.medicamentvet.utils.Static;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class represents the main scene of the application and creates the main components of the UI.
 */
public class MainPane extends BorderPane {

    public MainPane(Stage primaryStage, Application application) {

        MenuBarClass menuBarClass = new MenuBarClass();
        ToolBarClass toolBarClass = new ToolBarClass();
        VBox vBox = new VBox(menuBarClass, toolBarClass);
        setTop(vBox);

        TabPaneClass tabPaneClass = new TabPaneClass(primaryStage, application, menuBarClass, toolBarClass);
        setCenter(tabPaneClass);

        setBottom(new StatusBar());

        tabPaneClass.setApplicationData(false);

        // Check every 15 seconds and set the data of the application
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(Static.DELAY_15), actionEvent -> tabPaneClass.setApplicationData(false)));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        setOnMousePressed(mouseEvent -> requestFocus());
    }
}
