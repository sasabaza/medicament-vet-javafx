package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.gui.windows.WindowWarning;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class is a window that allows user to delete or add images from/to Rcp object.
 */
public class WindowRcpImage extends WindowClass {

    private final Scene scene;
    private final Button buttonUpdate;
    private final Button buttonReturn;
    private final Button buttonAddImage;
    private final HBox hBox;
    private final ObservableList<Node> componentList;
    private final TabPaneClass tabPaneClass;

    private int id;

    public WindowRcpImage(Stage primaryStage, WindowRcp windowRcp, String title, boolean resizable, double width, double height, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.tabPaneClass = tabPaneClass;

        buttonAddImage = new Button(Static.BUTTON_PLUS_TEXT);

        HBox hBoxAddImage = new HBox(buttonAddImage, Static.LABEL_IMAGE_RCP_IMAGE);
        hBoxAddImage.getStyleClass().add(Static.HBOX_ADD_IMAGE_CLASS_TEXT);

        buttonUpdate = getButtonOk();
        buttonUpdate.setText(Static.BUTTON_METTRE_A_JOUR_TEXT);

        buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBoxBottom = new HBox(buttonReturn, buttonUpdate);
        hBoxBottom.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        hBox = new HBox();

        componentList = hBox.getChildren();

        ScrollPane scrollPane = new ScrollPane(hBox);

        VBox vBoxRoot = new VBox(hBoxAddImage, scrollPane, hBoxBottom);

        scene = new Scene(vBoxRoot);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBoxRoot.getStyleClass().add(Static.WINDOW_RCP_IMAGE_CLASS_TEXT);

        getStage().setScene(scene);

        buttonUpdate.setOnAction(actionEvent -> buttonUpdateAction());

        buttonReturn.setOnAction(actionEvent -> {
            Medicament medicament = getMedicament();
            Rcp rcp = medicament.getRcp();
            medicament.setRcp(new Rcp(getImageURLList(), rcp.getDateValidation(), rcp.getLienRcp(), rcp.getTitreList(), rcp.getContenuList()));

            hideWindow();
            windowRcp.showWindow(primaryStage);
            windowRcp.init(medicament);
        });

        buttonAddImage.setOnAction(actionEvent -> {
            String selectedFileName = Utils.showFileChooserImage(getStage());

            if (selectedFileName != null) {

                buttonReturn.setDisable(false);
                buttonUpdate.setDisable(false);

                LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                createImagesAndAddToHBox(id, selectedFileName, hashSetError);

                if (hashSetError.size() > 0) {
                    String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                    WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_ERREUR, errorMessage);
                    windowWarning.showWindow();
                }
            }
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * Given the Medicament object parameter, the method shows a list of images.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        componentList.clear();
        id = 0;

        buttonReturn.setDisable(false);
        buttonUpdate.setDisable(false);
        buttonAddImage.setDisable(false);

        Rcp rcp = medicament.getRcp();
        List<String> imageList = rcp.getImageURLList();

        if (imageList != null) {
            LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

            for (int i = 0; i < imageList.size(); i++) {
                createImagesAndAddToHBox(i, imageList.get(i), hashSetError);
            }

            if (hashSetError.size() > 0) {
                String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_ERREUR, errorMessage);
                windowWarning.showWindow();
            }
        }
        buttonUpdate.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
    }

    /**
     * The method adds images to the HBox and each image is attached with a button to remove entire row (VBox).
     *
     * @param i            Number of the image
     * @param fileName     File name of the image
     * @param hashSetError Set of errors
     */
    private void createImagesAndAddToHBox(int i, String fileName, LinkedHashSet<String> hashSetError) {
    }

    /**
     * The method looks for the images added to the HBox and adds the path to a list of Strings.
     *
     * @return List of paths
     */
    private List<String> getImageURLList() {
        return new ArrayList<>();
    }

    /**
     * The method executes in a background thread the update of the Medicament object.
     */
    private void buttonUpdateAction() {
    }
}
