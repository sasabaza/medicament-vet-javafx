package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.simple.DatePickerClass;
import fr.medicamentvet.gui.simple.TextFieldClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.utils.Static;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The goal of the class is to show a window and permits user to edit most of the data of the Rcp object.
 */
public class WindowRcp extends WindowClass {

    private final Scene scene;
    private final DatePickerClass datePickerDateValidation;
    private final TextFieldClass fieldLienRcp;
    private final Stage primaryStage;
    private final Button buttonNext;
    private final ScrollPane scrollPane;
    private final WindowRcpImage windowRcpImage;
    private final VBox vBox;
    private final ObservableList<Node> componentList;

    public WindowRcp(Stage primaryStage, WindowModeleDestineVente windowModeleDestineVente, String title, boolean resizable, double width, double height, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.primaryStage = primaryStage;

        windowRcpImage = new WindowRcpImage(primaryStage, this, Static.WINDOW_RCP_IMAGE_TITRE, false, Static.WINDOW_RCP_IMAGE_WIDTH, Static.WINDOW_RCP_IMAGE_HEIGHT, tabPaneClass);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBox = new HBox(buttonReturn, buttonNext);
        hBox.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        datePickerDateValidation = new DatePickerClass(LocalDate.of(Static.DATE_BEGIN_YEAR, Static.DATE_BEGIN_MONTH_ONE, Static.DATE_BEGIN_DAY_ONE), Static.DATE_DE_VALIDATION);
        fieldLienRcp = new TextFieldClass(Static.LIEN_RCP_TEXT, Static.FIELD_CLASS_TEXT);

        vBox = new VBox();
        vBox.getStyleClass().add(Static.VBOX_COMPONENT_LIST_CLASS_TEXT);
        componentList = vBox.getChildren();

        scrollPane = new ScrollPane(vBox);

        VBox vBoxRoot = new VBox(scrollPane, hBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scene = new Scene(vBoxRoot);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBoxRoot.getStyleClass().add(Static.WINDOW_RCP_CLASS_TEXT);

        getStage().setScene(scene);

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        buttonReturn.setOnAction(actionEvent -> {
            Medicament medicament = getMedicament();
            Rcp rcp = medicament.getRcp();

            LocalDate dateValidation = datePickerDateValidation.getValue();

            String lienRcp = fieldLienRcp.getText().trim();

            List<String> contenuList = getContenuList();

            medicament.setRcp(new Rcp(rcp.getImageURLList(), dateValidation, lienRcp, rcp.getTitreList(), contenuList));

            hideWindow();
            windowModeleDestineVente.showWindow(primaryStage);
            windowModeleDestineVente.init(medicament);
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * The method constructs and initializes the layout of VBox considering the data of Rcp object.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        componentList.clear();

        Rcp rcp = medicament.getRcp();

        VBox vBoxDate = new VBox(Static.LABEL_DATE_VALIDATION, datePickerDateValidation);
        vBox.getChildren().addAll(vBoxDate, fieldLienRcp);

        LocalDate dateValidation = rcp.getDateValidation();
        datePickerDateValidation.setValue(dateValidation);

        String lienRcp = rcp.getLienRcp();
        fieldLienRcp.setText(lienRcp);

        List<String> titreList = rcp.getTitreList();
        List<String> contenuList = rcp.getContenuList();

        int size = titreList.size();

        for (int i = 0; i < size; i++) {
            createFieldsAndAddToVBox(titreList.get(i), contenuList.get(i));
        }
        buttonNext.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowRcpImage.setTheme(theme);
    }

    /**
     * The method creates and adds Labels and TextArea to the VBox layout.
     *
     * @param titre   Title
     * @param contenu Content
     */
    private void createFieldsAndAddToVBox(String titre, String contenu) {
    }

    /**
     * The method goes over the observable list of Nodes to obtain the text from each TextArea controls.
     *
     * @return List of Strings each TextArea
     */
    private List<String> getContenuList() {
        return new ArrayList<>();
    }

    /**
     * The method checks if there is an error with a particular field, then sets the Rcp object with the user inputs and displays the WindowRcpImage window.
     */
    private void buttonNextAction() {
    }
}
