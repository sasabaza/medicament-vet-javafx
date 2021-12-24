package fr.medicamentvet.gui.tabpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.simple.ComboBoxClass;
import fr.medicamentvet.gui.simple.TextClass;
import fr.medicamentvet.gui.windows.searchform.WindowDate;
import fr.medicamentvet.gui.windows.searchform.WindowEspece;
import fr.medicamentvet.gui.windows.searchform.WindowSubstanceActive;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import fr.medicamentvet.utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * This class represents the advanced search form component. The class contains binding listeners, and actions attached to the buttons.
 */
public class FormPane extends GridPane {

    private static LocalDate debutDate = LocalDate.of(Static.DATE_BEGIN_YEAR, Static.DATE_BEGIN_MONTH_ONE, Static.DATE_BEGIN_DAY_ONE);
    private static LocalDate finDate = LocalDate.now();
    private static String substanceActive1 = Static.EMPTY_TEXT;
    private static String substanceActive2 = Static.EMPTY_TEXT;
    private static List<String> especeSelectionList;

    private static ComboBoxClass formePharmaceutiqueComboBox;
    private static ComboBoxClass typeProcedureComboBox;
    private static ComboBoxClass conditionDelivranceComboBox;
    private static WindowDate windowDate;
    private static WindowEspece windowEspece;
    private static WindowSubstanceActive windowSubstanceActive;

    private final AutocompleteField fieldNomTitulaire;
    private final AutocompleteField fieldNumeroAMM;

    public FormPane(Stage primaryStage, SearchResult searchResult) {

        Static.BUTTON_RECHERCHER.getStyleClass().add(Static.BUTTON_SEARCH_CLASS_TEXT);

        TextClass textStartDate = new TextClass(Static.TEXTFLOW_CLASS_TEXT);
        TextClass textEndDate = new TextClass(Static.TEXTFLOW_CLASS_TEXT);

        TextClass textSubstanceActive1 = new TextClass(Static.TEXTFLOW_CLASS_TEXT);
        TextClass textSubstanceActive2 = new TextClass(Static.TEXTFLOW_CLASS_TEXT);

        TextClass textEspeces = new TextClass(Static.TEXTFLOW_CLASS_TEXT);

        TextFlow textFlowFormPane = new TextFlow(textStartDate, textEndDate, textSubstanceActive1, textSubstanceActive2, textEspeces);
        textFlowFormPane.setLineSpacing(Static.TEXT_LINE_SPACING);

        especeSelectionList = new ArrayList<>();

        fieldNomTitulaire = new AutocompleteField(this, Static.NOM_DU_TITULAIRE_TEXT, Static.NOM_DU_TITULAIRE_TEXT);
        fieldNumeroAMM = new AutocompleteField(this, Static.NUMERO_AMM_TEXT, Static.NUMERO_AMM_TEXT);

        formePharmaceutiqueComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);
        typeProcedureComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);
        conditionDelivranceComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);

        Label formePharmaceutiqueLabel = new Label(Static.LABEL_FORME_PHARMACEUTIQUE_TEXT);
        Label typeProcedureLabel = new Label(Static.LABEL_TYPE_DE_PROCEDURE_TEXT);
        Label conditionDelivranceLabel = new Label(Static.CONDITION_DELIVRANCE_SINGULIER_TEXT);

        windowDate = new WindowDate(primaryStage, Static.WINDOW_TITLE_DATE, false, Static.WINDOW_DATE_STAGE_WIDTH, Static.WINDOW_DATE_STAGE_HEIGHT);

        windowSubstanceActive = new WindowSubstanceActive(primaryStage, Static.SUBSTANCE_ACTIVE_PLURIEL_TEXT, false, Static.WINDOW_SUBSTANCE_ACTIVE_STAGE_WIDTH, Static.WINDOW_SUBSTANCE_ACTIVE_STAGE_HEIGHT);

        windowEspece = new WindowEspece(primaryStage, Static.ESPECE_DESTINATION_PLURIEL_TEXT, false, Static.WINDOW_ESPECE_STAGE_WIDTH, Static.WINDOW_ESPECE_STAGE_HEIGHT, Static.ESPECES_MAXIMUM_NUMBER);

        GridPane.setConstraints(fieldNomTitulaire, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_ZERO));
        GridPane.setConstraints(fieldNumeroAMM, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_TEN));

        GridPane.setConstraints(formePharmaceutiqueLabel, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));
        GridPane.setConstraints(typeProcedureLabel, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_TEN));

        GridPane.setConstraints(formePharmaceutiqueComboBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_ZERO));
        GridPane.setConstraints(typeProcedureComboBox, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_EIGHT, Static.INSET_TEN));

        GridPane.setConstraints(conditionDelivranceLabel, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_THREE, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));

        GridPane.setConstraints(conditionDelivranceComboBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FOUR, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_FIFTEEN, Static.INSET_ZERO));

        GridPane.setConstraints(Static.BUTTON_DATE_AMM, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_TWENTY_FIVE, Static.INSET_ZERO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.BUTTON_SUBSTANCE_ACTIVE, Static.COLUMN_INDEX_ONE, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_TWENTY_FIVE, Static.INSET_ZERO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.BUTTON_ESPECE, Static.COLUMN_INDEX_TWO, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.BUTTON_RECHERCHER, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.RIGHT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        GridPane.setConstraints(textFlowFormPane, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_SIX, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_TEN, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        getChildren().addAll(fieldNomTitulaire, fieldNumeroAMM, formePharmaceutiqueLabel, typeProcedureLabel, formePharmaceutiqueComboBox, typeProcedureComboBox, conditionDelivranceLabel, conditionDelivranceComboBox, Static.BUTTON_DATE_AMM, Static.BUTTON_SUBSTANCE_ACTIVE, Static.BUTTON_ESPECE, Static.BUTTON_RECHERCHER, textFlowFormPane);


        // Listen to the modification of the start date and end date values from WindowDate class
        textStartDate.textProperty().bind(Bindings.createStringBinding(() -> {
            debutDate = windowDate.getDatePickerStartDate().getValue();

            return Static.DATE_AMM_SPACE_DE_SPACE_TEXT + Utils.localDateToStringDateFR(debutDate);
        }, windowDate.getDatePickerStartDate().valueProperty()));

        textEndDate.textProperty().bind(Bindings.createStringBinding(() -> {
            finDate = windowDate.getDatePickerEndDate().getValue();

            return Static.DATE_AMM_AU_TEXT + Utils.localDateToStringDateFR(finDate);
        }, windowDate.getDatePickerEndDate().valueProperty()));


        // Listen to the modification of the inputs from WindowSubstanceActive class
        textSubstanceActive1.textProperty().bind(Bindings.createStringBinding(() -> {
            substanceActive1 = windowSubstanceActive.getFieldSubstanceActive1().getText();

            if (!substanceActive1.isEmpty()) {
                return Static.NEW_LINE_SUBSTANCE_ACTIVE_1_TEXT + substanceActive1;
            } else {
                return Static.EMPTY_TEXT;
            }
        }, windowSubstanceActive.getFieldSubstanceActive1().textProperty()));

        textSubstanceActive2.textProperty().bind(Bindings.createStringBinding(() -> {
            substanceActive2 = windowSubstanceActive.getFieldSubstanceActive2().getText();

            if (!substanceActive2.isEmpty()) {
                return Static.NEW_LINE_SUBSTANCE_ACTIVE_2_TEXT + substanceActive2;
            } else {
                return Static.EMPTY_TEXT;
            }
        }, windowSubstanceActive.getFieldSubstanceActive2().textProperty()));


        // Listen to the modification of selected items of the list view from WindowEspece class
        textEspeces.textProperty().bind(Bindings.createStringBinding(() -> {

            especeSelectionList = windowEspece.getObservableListSelection();

            StringBuilder stringBuilderEspece = new StringBuilder();

            if (especeSelectionList.size() > 0) {

                if (especeSelectionList.size() == 1) {
                    stringBuilderEspece.append(Static.PATTERN_NEWLINE + Static.ESPECE_COLON_SPACE_TEXT);
                } else {
                    stringBuilderEspece.append(Static.PATTERN_NEWLINE + Static.ESPECES_COLON_SPACE_TEXT);
                }

                stringBuilderEspece.append(String.join(Static.COMMA_SPACE_TEXT, especeSelectionList));

                return stringBuilderEspece.toString();
            } else {
                return Static.EMPTY_TEXT;
            }
        }, windowEspece.getListViewSelection().getItems()));

        Static.BUTTON_DATE_AMM.setOnAction(actionEvent -> {
            windowDate.showWindow(primaryStage);
            windowDate.init();
        });

        Static.BUTTON_SUBSTANCE_ACTIVE.setOnAction(actionEvent -> {
            windowSubstanceActive.showWindow(primaryStage);
            windowSubstanceActive.init();
        });

        Static.BUTTON_ESPECE.setOnAction(actionEvent -> {
            windowEspece.showWindow(primaryStage);
            windowEspece.init();
        });

        Static.BUTTON_RECHERCHER.setOnAction(actionEvent -> {
            Static.BUTTON_RECHERCHER.setDisable(true);

            String nomTitulaire = fieldNomTitulaire.getText().toUpperCase().trim();
            String numeroAMM = fieldNumeroAMM.getText().toUpperCase().trim();

            String formePharmaceutique = formePharmaceutiqueComboBox.getSelectionModel().getSelectedItem();
            String typeProcedure = typeProcedureComboBox.getSelectionModel().getSelectedItem();
            String conditionDelivrance = conditionDelivranceComboBox.getSelectionModel().getSelectedItem();

            MedicamentSearch medicamentSearch = new MedicamentSearch(nomTitulaire, numeroAMM, formePharmaceutique, typeProcedure, conditionDelivrance, debutDate, finDate, substanceActive1, substanceActive2, especeSelectionList, Static.SKIP_INITIAL);
            Controller.setSkip(Static.SKIP_INITIAL);

            searchResult.setEditorPaneContent(Static.BUTTON_RECHERCHER, medicamentSearch);
        });
    }

    public void setTheme(int theme) {
        windowDate.setTheme(theme);
        windowEspece.setTheme(theme);
        windowSubstanceActive.setTheme(theme);
    }

    /**
     * The aim of this method is to set the data needed to use the advanced form.
     *
     * @param manual Boolean true means that the background thread will always be executed
     */
    public void setFormPaneModel(boolean manual) {

        if (formePharmaceutiqueComboBox.getItems().size() == 1 || manual) {

            fieldNomTitulaire.setDisable(true);
            fieldNumeroAMM.setDisable(true);
            windowSubstanceActive.setFieldsDisable(true);

            formePharmaceutiqueComboBox.setDisable(true);
            typeProcedureComboBox.setDisable(true);
            conditionDelivranceComboBox.setDisable(true);

            formePharmaceutiqueComboBox.setItems(FXCollections.observableArrayList(Static.EMPTY_ARRAY));
            typeProcedureComboBox.setItems(FXCollections.observableArrayList(Static.EMPTY_ARRAY));
            conditionDelivranceComboBox.setItems(FXCollections.observableArrayList(Static.EMPTY_ARRAY));

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);

            TaskProducer taskProducer = new TaskProducer(Static.FORM_PANE_CLASS_TEXT, fieldNomTitulaire, fieldNumeroAMM, formePharmaceutiqueComboBox, typeProcedureComboBox, conditionDelivranceComboBox, windowEspece, windowSubstanceActive);
            new Thread(taskProducer).start();
        }
    }
}
