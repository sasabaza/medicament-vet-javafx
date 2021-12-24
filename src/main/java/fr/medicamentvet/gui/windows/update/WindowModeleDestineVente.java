package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.ModeleDestineVente;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.simple.TextFieldClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class allows user to edit the information of ModeleDestineVente object. User may either continue to the next window or update Medicament object.
 */
public class WindowModeleDestineVente extends WindowClass {

    private final Scene scene;
    private final Stage primaryStage;
    private final Button buttonNext;
    private final Button buttonAddModeleDestineVente;
    private final Label labelModeleDestineVente;
    private final VBox vBox;
    private final ObservableList<Node> componentList;
    private final WindowRcp windowRcp;
    private final int maxCount;
    private final TabPaneClass tabPaneClass;

    private boolean numeroAMMFR;
    private int id;

    public WindowModeleDestineVente(Stage primaryStage, WindowConditionnementPrimaireCodeATC windowConditionnementPrimaireCodeATC, String title, boolean resizable, double width, double height, int maxCount, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.primaryStage = primaryStage;
        this.maxCount = maxCount;
        this.tabPaneClass = tabPaneClass;

        windowRcp = new WindowRcp(primaryStage, this, Static.WINDOW_RCP_TITRE, true, Static.WINDOW_RCP_WIDTH, Static.WINDOW_RCP_HEIGHT, tabPaneClass);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBox = new HBox(buttonReturn, buttonNext);
        hBox.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        buttonAddModeleDestineVente = new Button(Static.BUTTON_PLUS_TEXT);
        labelModeleDestineVente = new Label();
        HBox hBoxAddModeleDestineVente = new HBox(buttonAddModeleDestineVente, labelModeleDestineVente);
        hBoxAddModeleDestineVente.getStyleClass().add(Static.HBOX_ADD_MODELE_DESTINE_VENTE_CLASS_TEXT);

        vBox = new VBox();
        componentList = vBox.getChildren();

        ScrollPane scrollPane = new ScrollPane(vBox);

        VBox vBoxRoot = new VBox(hBoxAddModeleDestineVente, scrollPane, hBox);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scene = new Scene(vBoxRoot);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBoxRoot.getStyleClass().add(Static.WINDOW_MODELE_DESTINE_VENTE_CLASS_TEXT);

        getStage().setScene(scene);

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        buttonReturn.setOnAction(actionEvent -> {
            List<ModeleDestineVente> modeleDestineVenteList = new ArrayList<>();

            Medicament medicament = getMedicament();
            String numeroAMM = medicament.getNumeroAMM();

            for (int i = 0; i < componentList.size(); i++) {
                HBox hBoxNode = (HBox) componentList.get(i);
                ObservableList<Node> nodes = hBoxNode.getChildren();

                TextFieldClass textFieldClass = (TextFieldClass) nodes.get(0);
                String libelle = textFieldClass.getText().trim();

                String codeGTIN = null;

                textFieldClass = (TextFieldClass) nodes.get(1);
                if (textFieldClass.getText() != null) {
                    codeGTIN = textFieldClass.getText().trim();
                }

                if (!numeroAMMFR) {
                    textFieldClass = (TextFieldClass) nodes.get(2);
                    numeroAMM = textFieldClass.getText().trim();
                }

                modeleDestineVenteList.add(new ModeleDestineVente(i + 1, libelle, codeGTIN, numeroAMM));
            }

            medicament.setModeleDestineVenteList(modeleDestineVenteList);

            hideWindow();
            windowConditionnementPrimaireCodeATC.showWindow(primaryStage);
            windowConditionnementPrimaireCodeATC.init(medicament);
        });

        buttonAddModeleDestineVente.setOnAction(actionEvent -> {
            Utils.buttonEnable(componentList, false);

            id++;

            createFieldsAndAddToVBox(id, null);

            if (componentList.size() >= maxCount) {
                buttonAddModeleDestineVente.setDisable(true);
            }
        });

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });
    }

    /**
     * The method displays the main information of ModeleDestineVente object, user may edit the information.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        componentList.clear();

        Rcp rcp = medicament.getRcp();

        if (rcp == null) {
            buttonNext.setText(Static.BUTTON_METTRE_A_JOUR_TEXT);
        } else {
            buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);
        }

        String numeroAMM = medicament.getNumeroAMM();

        List<ModeleDestineVente> modeleDestineVenteList = medicament.getModeleDestineVenteList();
        id = modeleDestineVenteList.size();

        if (numeroAMM != null && numeroAMM.startsWith(Static.FR_TEXT)) {
            numeroAMMFR = true;
            labelModeleDestineVente.setText(Static.LIBELLE_CODE_GTIN_TEXT);
        } else {
            numeroAMMFR = false;
            labelModeleDestineVente.setText(Static.LIBELLE_CODE_GTIN_NUMERO_AMM_TEXT);
        }

        for (int i = 0; i < modeleDestineVenteList.size(); i++) {
            createFieldsAndAddToVBox(i, modeleDestineVenteList);
        }

        Utils.buttonEnable(componentList, true);
        buttonNext.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowRcp.setTheme(theme);
    }

    /**
     * The method creates fields and a button, and adds them to the VBox.
     *
     * @param i                      Index of the list
     * @param modeleDestineVenteList List of ModeleDestineVente object
     */
    private void createFieldsAndAddToVBox(int i, List<ModeleDestineVente> modeleDestineVenteList) {
    }

    /**
     * The method verifies whether true or false there is an error, extracts input from the fields and sets the modeleDestineVenteList object. The end part of the method may update Medicament object or displays the next window.
     */
    private void buttonNextAction() {
    }
}
