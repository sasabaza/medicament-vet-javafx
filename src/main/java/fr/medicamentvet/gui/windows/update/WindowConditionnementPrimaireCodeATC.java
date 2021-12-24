package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.utils.Static;
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
 * The class contains the information regarding the Conditionnement primaire and the code ATCVET.
 */
public class WindowConditionnementPrimaireCodeATC extends WindowClass {

    private final Scene scene;
    private final Button buttonAddConditionnementPrimaire;
    private final Button buttonAddCodeATCVET;
    private final VBox vBoxConditionnementPrimaire;
    private final ObservableList<Node> componentConditionnementPrimaireList;
    private final VBox vBoxCodeATCVET;
    private final ObservableList<Node> componentCodeATCVETList;
    private final Button buttonNext;
    private final Stage primaryStage;
    private final WindowModeleDestineVente windowModeleDestineVente;
    private final int maxCount;

    private int idConditionnementPrimaire;
    private int idCodeATCVET;

    public WindowConditionnementPrimaireCodeATC(Stage primaryStage, WindowComposition windowComposition, String title, boolean resizable, double width, double height, int maxCount, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.primaryStage = primaryStage;
        this.maxCount = maxCount;

        windowModeleDestineVente = new WindowModeleDestineVente(primaryStage, this, Static.WINDOW_MODELE_DESTINE_VENTE_TITRE, true, Static.WINDOW_MODELE_DESTINE_VENTE_WIDTH, Static.WINDOW_MODELE_DESTINE_VENTE_HEIGHT, Static.MODELE_DESTINE_VENTE_MAXIMUM_NUMBER, tabPaneClass);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBox = new HBox(buttonReturn, buttonNext);
        hBox.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        buttonAddConditionnementPrimaire = new Button(Static.BUTTON_PLUS_TEXT);
        HBox hBoxAddConditionnementPrimaire = new HBox(buttonAddConditionnementPrimaire, Static.LABEL_CONDITIONNEMENT_PRIMAIRE);
        hBoxAddConditionnementPrimaire.getStyleClass().add(Static.HBOX_ADD_CONDITIONNEMENT_PRIMAIRE_TEXT);

        vBoxConditionnementPrimaire = new VBox();

        componentConditionnementPrimaireList = vBoxConditionnementPrimaire.getChildren();

        ScrollPane scrollPaneConditionnementPrimaire = new ScrollPane(vBoxConditionnementPrimaire);

        buttonAddCodeATCVET = new Button(Static.BUTTON_PLUS_TEXT);
        HBox hBoxAddCodeATCVET = new HBox(buttonAddCodeATCVET, Static.LABEL_CODE_ATCVET);
        hBoxAddCodeATCVET.getStyleClass().add(Static.HBOX_ADD_CODE_ATCVET_CLASS_TEXT);

        vBoxCodeATCVET = new VBox();

        componentCodeATCVETList = vBoxCodeATCVET.getChildren();

        ScrollPane scrollPaneCodeATCVET = new ScrollPane(vBoxCodeATCVET);

        VBox vBoxRoot = new VBox(hBoxAddConditionnementPrimaire, scrollPaneConditionnementPrimaire, hBoxAddCodeATCVET, scrollPaneCodeATCVET, hBox);
        VBox.setVgrow(scrollPaneConditionnementPrimaire, Priority.ALWAYS);
        VBox.setVgrow(scrollPaneCodeATCVET, Priority.ALWAYS);

        scene = new Scene(vBoxRoot);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBoxRoot.getStyleClass().add(Static.WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_CLASS_TEXT);

        getStage().setScene(scene);

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        buttonReturn.setOnAction(actionEvent -> {
            List<String> conditionnementPrimaireList = textFieldComponentToList(componentConditionnementPrimaireList);

            List<String> codeATCVETList = textFieldComponentToList(componentCodeATCVETList);

            Medicament medicament = getMedicament();

            medicament.setConditionnementPrimaireList(conditionnementPrimaireList);
            medicament.setCodeATCVETList(codeATCVETList);

            hideWindow();
            windowComposition.showWindow(primaryStage);
            windowComposition.init(medicament);
        });

        buttonAddConditionnementPrimaire.setOnAction(actionEvent -> {
            idConditionnementPrimaire++;

            createFieldsAndAddToVBox(idConditionnementPrimaire, null, vBoxConditionnementPrimaire, componentConditionnementPrimaireList, Static.CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT);

            if (componentConditionnementPrimaireList.size() >= maxCount) {
                buttonAddConditionnementPrimaire.setDisable(true);
            }
        });

        buttonAddCodeATCVET.setOnAction(actionEvent -> {
            idCodeATCVET++;

            createFieldsAndAddToVBox(idCodeATCVET, null, vBoxCodeATCVET, componentCodeATCVETList, Static.CODE_ATCVET_SINGULIER_TEXT);

            if (componentCodeATCVETList.size() >= maxCount) {
                buttonAddCodeATCVET.setDisable(true);
            }
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * The method puts components to the VBoxs, and initializes the text of the fields.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        componentConditionnementPrimaireList.clear();
        List<String> conditionnementPrimaireList = medicament.getConditionnementPrimaireList();

        if (conditionnementPrimaireList != null) {
            idConditionnementPrimaire = conditionnementPrimaireList.size();

            for (int i = 0; i < conditionnementPrimaireList.size(); i++) {
                createFieldsAndAddToVBox(i, conditionnementPrimaireList, vBoxConditionnementPrimaire, componentConditionnementPrimaireList, Static.CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT);
            }
        } else {
            idConditionnementPrimaire = 0;
        }

        componentCodeATCVETList.clear();
        List<String> codeATCVETList = medicament.getCodeATCVETList();

        if (codeATCVETList != null) {
            idCodeATCVET = codeATCVETList.size();

            for (int i = 0; i < codeATCVETList.size(); i++) {
                createFieldsAndAddToVBox(i, codeATCVETList, vBoxCodeATCVET, componentCodeATCVETList, Static.CODE_ATCVET_SINGULIER_TEXT);
            }
        } else {
            idCodeATCVET = 0;
        }

        buttonNext.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowModeleDestineVente.setTheme(theme);
    }

    /**
     * The method creates the components and adds them to the respective VBox.
     *
     * @param i              Index of the list
     * @param list           List of Strings either a list of conditionnement primaire or list of code ATCVET
     * @param vBox           VBox layout
     * @param observableList List of Nodes (components)
     * @param text           Prompt text
     */
    private void createFieldsAndAddToVBox(int i, List<String> list, VBox vBox, ObservableList<Node> observableList, String text) {
    }

    /**
     * The method extracts the text for each field and returns a list of Strings.
     *
     * @param observableList List of Nodes (components)
     * @return List of Strings
     */
    private List<String> textFieldComponentToList(ObservableList<Node> observableList) {
        return new ArrayList<>();
    }

    /**
     * The method makes sure that there is no error and moves to the next window.
     */
    private void buttonNextAction() {
    }
}
