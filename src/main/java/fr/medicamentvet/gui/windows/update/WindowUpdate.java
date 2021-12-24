package fr.medicamentvet.gui.windows.update;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.simple.ComboBoxClass;
import fr.medicamentvet.gui.simple.DatePickerClass;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.gui.windows.WindowWarning;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import fr.medicamentvet.utils.Utils;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * The class is a window allowing user to modify the base information of the medicament.
 */
public class WindowUpdate extends WindowClass {

    private final static ImageView IMAGE_ICON = new ImageView();

    private final Scene scene;
    private final Button buttonAddImage;
    private final Button buttonRemoveImage;
    private final Button buttonNext;
    private final Button buttonCancel;
    private final WindowEspeceUpdate windowEspeceUpdate;
    private final AutocompleteField fieldNom;
    private final AutocompleteField fieldNomTitulaire;
    private final ComboBoxClass formePharmaceutiqueComboBox;
    private final ComboBoxClass statutAutorisationComboBox;
    private final ComboBoxClass typeProcedureComboBox;
    private final DatePickerClass datePickerDateAMM;
    private final Stage primaryStage;

    public WindowUpdate(Stage primaryStage, String title, boolean resizable, double width, double height, TabPaneClass tabPaneClass) {
        super(primaryStage, title, resizable, width, height);

        this.primaryStage = primaryStage;

        GridPane gridPane = new GridPane();

        windowEspeceUpdate = new WindowEspeceUpdate(primaryStage, this, Static.WINDOW_ESPECE_TITRE, false, Static.WINDOW_ESPECE_UPDATE_STAGE_WIDTH, Static.WINDOW_ESPECE_UPDATE_STAGE_HEIGHT, Static.ESPECES_MAXIMUM_NUMBER, tabPaneClass);

        fieldNom = new AutocompleteField(gridPane, Static.NOM_DU_MEDICAMENT_TEXT, Static.NOM_DU_MEDICAMENT_TEXT);
        fieldNomTitulaire = new AutocompleteField(gridPane, Static.NOM_DU_TITULAIRE_TEXT, Static.NOM_DU_TITULAIRE_TEXT);

        datePickerDateAMM = new DatePickerClass(LocalDate.of(Static.DATE_BEGIN_YEAR, Static.DATE_BEGIN_MONTH_ONE, Static.DATE_BEGIN_DAY_ONE), Static.DATE_AMM_TEXT);

        formePharmaceutiqueComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);
        statutAutorisationComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);
        typeProcedureComboBox = new ComboBoxClass(Static.EMPTY_ARRAY, Static.ROW_MAXIMUM_NUMBER);

        buttonCancel = getButtonCancel();
        buttonCancel.setText(Static.BUTTON_ANNULER_TEXT);

        buttonNext = getButtonOk();
        buttonNext.setText(Static.BUTTON_SUIVANT_TEXT);

        HBox hBoxBottom = new HBox(buttonCancel, buttonNext);
        hBoxBottom.getStyleClass().add(Static.HBOX_BOTTOM_CLASS_TEXT);

        buttonAddImage = new Button(Static.BUTTON_PLUS_TEXT);
        buttonRemoveImage = new Button(Static.BUTTON_MINUS_TEXT);

        HBox hBoxButtonsImage = new HBox(Static.LABEL_IMAGE_UPDATE, buttonAddImage, buttonRemoveImage);
        hBoxButtonsImage.getStyleClass().add(Static.HBOX_BOTTOMS_IMAGE_CLASS_TEXT);

        HBox hBoxImage = new HBox(IMAGE_ICON);
        hBoxImage.getStyleClass().add(Static.HBOX_IMAGE_CLASS_TEXT);

        GridPane.setConstraints(fieldNom, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ZERO, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        GridPane.setConstraints(fieldNomTitulaire, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_ONE, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        GridPane.setConstraints(Static.LABEL_FORME_PHARMACEUTIQUE, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.LABEL_DATE_AMM, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_TWO, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_TEN));

        GridPane.setConstraints(formePharmaceutiqueComboBox, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_THREE, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));
        GridPane.setConstraints(datePickerDateAMM, Static.COLUMN_INDEX_THREE, Static.ROW_INDEX_THREE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_TEN));

        GridPane.setConstraints(hBoxButtonsImage, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FOUR, Static.COLUMNS_SPAN_FOUR, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN, Static.INSET_ZERO));

        GridPane.setConstraints(hBoxImage, Static.COLUMN_INDEX_ZERO, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_THREE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.LABEL_STATUT_AUTORISATION, Static.COLUMN_INDEX_ONE, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_ZERO));
        GridPane.setConstraints(Static.LABEL_TYPE_PROCEDURE, Static.COLUMN_INDEX_TWO, Static.ROW_INDEX_FIVE, Static.COLUMNS_SPAN_TWO, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TWO, Static.INSET_TEN));

        GridPane.setConstraints(statutAutorisationComboBox, Static.COLUMN_INDEX_ONE, Static.ROW_INDEX_SIX, Static.COLUMNS_SPAN_ONE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));
        GridPane.setConstraints(typeProcedureComboBox, Static.COLUMN_INDEX_TWO, Static.ROW_INDEX_SIX, Static.COLUMNS_SPAN_TWO, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_TEN));

        GridPane.setConstraints(hBoxBottom, Static.COLUMN_INDEX_ONE, Static.ROW_INDEX_EIGHT, Static.COLUMNS_SPAN_THREE, Static.ROWS_SPAN_ONE, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO, Static.INSET_ZERO));

        gridPane.getChildren().addAll(fieldNom, fieldNomTitulaire, Static.LABEL_FORME_PHARMACEUTIQUE, Static.LABEL_DATE_AMM, formePharmaceutiqueComboBox, datePickerDateAMM, hBoxButtonsImage, hBoxImage, Static.LABEL_STATUT_AUTORISATION, Static.LABEL_TYPE_PROCEDURE, statutAutorisationComboBox, typeProcedureComboBox, hBoxBottom);

        scene = new Scene(gridPane);
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[Controller.getTheme()]);

        gridPane.getStyleClass().add(Static.WINDOW_UPDATE_CLASS_TEXT);

        getStage().setScene(scene);

        buttonAddImage.setOnAction(actionEvent -> {
            String selectedFileName = Utils.showFileChooserImage(getStage());

            if (selectedFileName != null) {

                if (selectedFileName.length() > Static.IMAGE_MAX_CHARACTERS) {
                    WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_ERREUR, Static.ERROR_MESSAGE_PATH_TOO_LONG_TEXT);
                    windowWarning.showWindow();
                } else {

                    buttonNext.setDisable(false);
                    buttonCancel.setDisable(false);
                    buttonAddImage.setDisable(true);
                    buttonRemoveImage.setDisable(false);

                    LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                    Image image = Utils.imageResize(selectedFileName, buttonNext, buttonCancel, buttonAddImage, buttonRemoveImage, hashSetError);

                    if (image != null) {
                        Medicament medicament = getMedicament();
                        medicament.setImageURL(selectedFileName);
                    }

                    if (hashSetError.size() > 0) {
                        String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                        WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_ERREUR, errorMessage);
                        windowWarning.showWindow();
                    }

                    IMAGE_ICON.setImage(image);
                }
            } else {
                buttonAddImage.setDisable(false);
                buttonRemoveImage.setDisable(true);

                IMAGE_ICON.setImage(null);
            }
        });

        buttonRemoveImage.setOnAction(actionEvent -> {
            Medicament medicament = getMedicament();

            buttonAddImage.setDisable(false);
            buttonRemoveImage.setDisable(true);

            IMAGE_ICON.setImage(null);

            medicament.setImageURL(null);
        });

        buttonNext.setOnAction(actionEvent -> buttonNextAction());

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE) {
                hideWindow();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        scene.setOnMousePressed(mouseEvent -> gridPane.requestFocus());
    }

    /**
     * The method initializes the window with the base information of the Medicament object.
     *
     * @param medicament Medicament object
     */
    public void init(Medicament medicament) {
        setMedicament(medicament);

        if (medicament != null) {
            buttonAddImage.setDisable(false);
            buttonRemoveImage.setDisable(true);
            buttonNext.setDisable(false);
            buttonCancel.setDisable(false);

            IMAGE_ICON.setImage(null);

            String imageURL = medicament.getImageURL();

            if (imageURL != null) {

                buttonAddImage.setDisable(true);
                buttonRemoveImage.setDisable(false);

                LinkedHashSet<String> hashSetError = new LinkedHashSet<>();

                Image image = Utils.imageResize(imageURL, buttonNext, buttonCancel, buttonAddImage, buttonRemoveImage, hashSetError);

                if (hashSetError.size() > 0) {
                    String errorMessage = String.join(Static.PATTERN_NEWLINE, hashSetError);

                    WindowWarning windowWarning = new WindowWarning(getStage(), Static.WINDOW_WARNING_TITLE_ERREUR, errorMessage);
                    windowWarning.showWindow();

                    medicament.setImageURL(null);
                }

                IMAGE_ICON.setImage(image);
            }

            String nom = medicament.getNom();

            String[] arrayNom = Utils.nomIdMapToArrayNomSorted();
            fieldNom.setArrayString(arrayNom);
            fieldNom.setAutocompleteFieldText(nom);
            fieldNom.getStyleClass().removeAll(Static.ERROR_FIELD_CLASS_TEXT);

            String nomTitulaire = medicament.getNomTitulaire();
            fieldNomTitulaire.setAutocompleteFieldText(nomTitulaire);
            fieldNomTitulaire.getStyleClass().removeAll(Static.ERROR_FIELD_CLASS_TEXT);

            String formePharmaceutique = medicament.getFormePharmaceutique();
            formePharmaceutiqueComboBox.getSelectionModel().select(formePharmaceutique);

            LocalDate dateAMM = medicament.getDateAMM();
            datePickerDateAMM.setValue(dateAMM);

            String statutAutorisation = medicament.getStatutAutorisation();
            statutAutorisationComboBox.getSelectionModel().select(statutAutorisation);

            String typeProcedure = medicament.getTypeProcedure();
            typeProcedureComboBox.getSelectionModel().select(typeProcedure);

            buttonNext.requestFocus();
        } else {
            buttonAddImage.setDisable(true);
            buttonRemoveImage.setDisable(true);
            buttonNext.setDisable(true);
            buttonCancel.setDisable(true);
        }
    }

    /**
     * The objective of the method is to perform a task in a background thread to set the data needed to edit the base information of a medicament.
     *
     * @param manual Boolean true means that the background thread will always be executed
     */
    public void setVariables(boolean manual) {

        if (fieldNomTitulaire.getArrayString() == null || manual) {
            fieldNomTitulaire.setDisable(true);
            typeProcedureComboBox.setDisable(true);
            statutAutorisationComboBox.setDisable(true);
            formePharmaceutiqueComboBox.setDisable(true);

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);

            TaskProducer taskProducer = new TaskProducer(Static.WINDOW_UPDATE_CLASS_TEXT, fieldNomTitulaire, typeProcedureComboBox, statutAutorisationComboBox, formePharmaceutiqueComboBox);
            new Thread(taskProducer).start();
        }
    }

    public void setTheme(int theme) {
        scene.getStylesheets().setAll(Static.URL_STYLE_SHEET_WINDOW[theme]);
        windowEspeceUpdate.setTheme(theme);
    }

    /**
     * The method checks that the information entered is valid and then displays the next window.
     */
    private void buttonNextAction() {
    }
}
