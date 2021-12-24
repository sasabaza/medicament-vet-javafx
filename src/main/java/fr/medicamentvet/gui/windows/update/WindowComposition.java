package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Composition;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The class represents a window where user can insert or delete text relevant to the composition of a medicament.
 */
public class WindowComposition extends WindowClass {

    private final Scene scene;
    private final Stage primaryStage;
    private final Button buttonNext;
    private final WindowConditionnementPrimaireCodeATC windowConditionnementPrimaireCodeATC;
    private final Button buttonAddSubstanceActive;
    private final VBox vBox;
    private final ObservableList<Node> componentList;
    private final int maxCount;

    private int id;

    public WindowComposition(Stage primaryStage, WindowExcipientQSP windowExcipientQSP, String title, boolean resizable, double width, double height, int maxCount, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.primaryStage = primaryStage;
        this.maxCount = maxCount;

        windowConditionnementPrimaireCodeATC = new WindowConditionnementPrimaireCodeATC(primaryStage, this, Static.WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_TITRE, true, Static.WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_WIDTH, Static.WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_HEIGHT, Static.CONDITIONNEMENT_PRIMAIRE_CODE_ATC_MAXIMUM_NUMBER, tabPaneClass);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        Button buttonReturn = new Button(Static.BUTTON_RETOUR_TEXT);

        HBox hBox = new HBox(buttonReturn, buttonNext);
        hBox.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        buttonAddSubstanceActive = new Button(Static.BUTTON_PLUS_TEXT);

        HBox hBoxAddSubstanceActive = new HBox(buttonAddSubstanceActive, Static.LABEL_COMPOSITION);
        hBoxAddSubstanceActive.getStyleClass().add(Static.HBOX_ADD_SUBSTANCE_ACTIVE_CLASS_TEXT);

        vBox = new VBox();

        componentList = vBox.getChildren();

        ScrollPane scrollPane = new ScrollPane(vBox);

        VBox vBoxRoot = new VBox(hBoxAddSubstanceActive, scrollPane, hBox);

        scene = new Scene(vBoxRoot);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        vBoxRoot.getStyleClass().add(Static.WINDOW_COMPOSITION_CLASS_TEXT);

        getStage().setScene(scene);

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        buttonReturn.setOnAction(actionEvent -> {
            List<Composition> compositionList = new ArrayList<>();

            for (int i = 0; i < componentList.size(); i++) {
                HBox hBoxNode = (HBox) componentList.get(i);
                ObservableList<Node> nodes = hBoxNode.getChildren();

                AutocompleteField autocompleteField = (AutocompleteField) nodes.get(0);
                String substanceActive = autocompleteField.getText().trim();

                String quantite = null;

                TextFieldClass textFieldClass = (TextFieldClass) nodes.get(1);
                if (textFieldClass.getText() != null) {
                    quantite = textFieldClass.getText().trim();
                }

                String unite = null;

                textFieldClass = (TextFieldClass) nodes.get(2);
                if (textFieldClass.getText() != null) {
                    unite = textFieldClass.getText().trim();
                }

                compositionList.add(new Composition(i + 1, substanceActive, quantite, unite));
            }

            Medicament medicament = getMedicament();
            medicament.setCompositionList(compositionList);

            hideWindow();
            windowExcipientQSP.showWindow(primaryStage);
            windowExcipientQSP.init(medicament);
        });

        buttonAddSubstanceActive.setOnAction(actionEvent -> {
            Utils.buttonEnable(componentList, false);

            id++;

            createFieldsAndAddToVBox(id, null);

            if (componentList.size() >= maxCount) {
                buttonAddSubstanceActive.setDisable(true);
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scrollPane.vvalueProperty().addListener((observableValue, oldValue, newValue) -> scrollPane.requestFocus());

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scene.setOnMousePressed(mouseEvent -> vBoxRoot.requestFocus());
    }

    /**
     * The purpose of the method is to create components and add them to the VBox.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        componentList.clear();

        List<Composition> compositionList = medicament.getCompositionList();
        id = compositionList.size();

        buttonAddSubstanceActive.setDisable(false);

        for (int i = 0; i < compositionList.size(); i++) {
            createFieldsAndAddToVBox(i, compositionList);
        }

        Utils.buttonEnable(componentList, true);
        buttonNext.requestFocus();
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowConditionnementPrimaireCodeATC.setTheme(theme);
    }

    /**
     * The method creates necessary fields to modify the text, and a button that is added to the row of components.
     *
     * @param i               Index of the list
     * @param compositionList List of Composition objects
     */
    private void createFieldsAndAddToVBox(int i, List<Composition> compositionList) {
    }

    /**
     * The goal of the method is to validate the text and shows the next window.
     */
    private void buttonNextAction() {
    }
}
