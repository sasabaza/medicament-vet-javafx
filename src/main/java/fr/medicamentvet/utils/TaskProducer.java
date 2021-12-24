package fr.medicamentvet.utils;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.entities.SearchForm;
import fr.medicamentvet.entities.UpdateForm;
import fr.medicamentvet.gui.TabPaneClass;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.simple.ComboBoxClass;
import fr.medicamentvet.gui.tabpane.FieldPane;
import fr.medicamentvet.gui.tabpane.TableResult;
import fr.medicamentvet.gui.windows.WindowClass;
import fr.medicamentvet.gui.windows.WindowDelete;
import fr.medicamentvet.gui.windows.searchform.WindowEspece;
import fr.medicamentvet.gui.windows.searchform.WindowSubstanceActive;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 * The purpose of this class is to manage tasks in a background thread. Typically, when there is long-running process, we want the UI of the application to remain responsive.
 */
public class TaskProducer extends Task<Object> {

    private final String className;
    private AutocompleteField fieldNom;
    private WindowDelete windowDelete;

    private Button buttonDelete;
    private FieldPane fieldPane;

    private int id;
    private WebEngine webEngine;
    private VBox webViewBox;
    private Timeline timeline;
    private Button buttonUpdate;
    private ToggleButton rcpToggleButton;
    private ToggleButton favoriteToggleButton;
    private TableResult tableResult;

    private Button buttonSearch;
    private MedicamentSearch medicamentSearch;

    private AutocompleteField fieldNomTitulaire;
    private AutocompleteField fieldNumeroAMM;
    private ComboBoxClass formePharmaceutiqueComboBox;
    private ComboBoxClass typeProcedureComboBox;
    private ComboBoxClass conditionDelivranceComboBox;
    private ComboBoxClass statutAutorisationComboBox;
    private WindowEspece windowEspece;
    private WindowSubstanceActive windowSubstanceActive;

    private Medicament medicament;
    private WindowClass windowClass;
    private TabPaneClass tabPaneClass;

    private PrinterJob printerJob;
    private PDDocument document;

    public TaskProducer(String className, AutocompleteField fieldNom, WindowDelete windowDelete) {
        this.className = className;
        this.fieldNom = fieldNom;
        this.windowDelete = windowDelete;
    }

    public TaskProducer(String className, Button buttonDelete, int id, TableResult tableResult, FieldPane fieldPane) {
        this.className = className;
        this.buttonDelete = buttonDelete;
        this.id = id;
        this.tableResult = tableResult;
        this.fieldPane = fieldPane;
    }

    public TaskProducer(String className, AutocompleteField fieldNom, int id, WebEngine webEngine, VBox webViewBox, Timeline timeline, Button buttonUpdate, ToggleButton rcpToggleButton, ToggleButton favoriteToggleButton, TableResult tableResult) {
        this.className = className;
        this.fieldNom = fieldNom;
        this.id = id;
        this.webEngine = webEngine;
        this.webViewBox = webViewBox;
        this.timeline = timeline;
        this.buttonUpdate = buttonUpdate;
        this.rcpToggleButton = rcpToggleButton;
        this.favoriteToggleButton = favoriteToggleButton;
        this.tableResult = tableResult;
    }

    public TaskProducer(String className, AutocompleteField fieldNomTitulaire, ComboBoxClass typeProcedureComboBox, ComboBoxClass statutAutorisationComboBox, ComboBoxClass formePharmaceutiqueComboBox) {
        this.className = className;
        this.fieldNomTitulaire = fieldNomTitulaire;
        this.typeProcedureComboBox = typeProcedureComboBox;
        this.statutAutorisationComboBox = statutAutorisationComboBox;
        this.formePharmaceutiqueComboBox = formePharmaceutiqueComboBox;
    }

    public TaskProducer(String className, Button buttonSearch, MedicamentSearch medicamentSearch, WebEngine webEngine, VBox webViewBox, Timeline timeline) {
        this.className = className;
        this.buttonSearch = buttonSearch;
        this.medicamentSearch = medicamentSearch;
        this.webEngine = webEngine;
        this.webViewBox = webViewBox;
        this.timeline = timeline;
    }

    public TaskProducer(String className, MedicamentSearch medicamentSearch, WebEngine webEngine) {
        this.className = className;
        this.medicamentSearch = medicamentSearch;
        this.webEngine = webEngine;
    }

    public TaskProducer(String className, AutocompleteField fieldNomTitulaire, AutocompleteField fieldNumeroAMM, ComboBoxClass formePharmaceutiqueComboBox, ComboBoxClass typeProcedureComboBox, ComboBoxClass conditionDelivranceComboBox, WindowEspece windowEspece, WindowSubstanceActive windowSubstanceActive) {
        this.className = className;
        this.fieldNomTitulaire = fieldNomTitulaire;
        this.fieldNumeroAMM = fieldNumeroAMM;
        this.formePharmaceutiqueComboBox = formePharmaceutiqueComboBox;
        this.typeProcedureComboBox = typeProcedureComboBox;
        this.conditionDelivranceComboBox = conditionDelivranceComboBox;
        this.windowEspece = windowEspece;
        this.windowSubstanceActive = windowSubstanceActive;
    }

    public TaskProducer(String className, PrinterJob printerJob, PDDocument document) {
        this.className = className;
        this.printerJob = printerJob;
        this.document = document;
    }

    @Override
    protected Object call() throws IOException, InterruptedException, PrinterException {
        Object object = new Object();

        switch (className) {
            case Static.FIELD_PANE_CLASS_TEXT:
                object = Controller.getAllMedicamentsNomId();
                break;
            case Static.FORM_PANE_CLASS_TEXT:
                object = Controller.getSearchFormInputs();
                break;
            case Static.WINDOW_UPDATE_CLASS_TEXT:
                object = Controller.getUpdateFormInputs();
                break;
            case Static.WINDOW_DELETE_CLASS_TEXT:
                Controller.deleteMedicamentById(id);
                break;
            case Static.WINDOW_SAVE_UPDATE_TEXT:
                object = Controller.updateMedicament(medicament);
                break;
            case Static.SEARCH_RESULT_CLASS_TEXT:
            case Static.SEARCH_RESULT_SKIP_CLASS_TEXT:
                object = Controller.searchMedicamentNames(medicamentSearch);
                break;
            case Static.TABLE_RESULT_CLASS_TEXT:
                object = Controller.getMedicamentById(id);
                break;
            case Static.SEARCH_RESULT_PRINTER_CLASS_TEXT:
                printAction(Static.TITLE_DIALOG_RECHERCHE_PRINT, printerJob);
                break;
            case Static.TABLE_RESULT_PRINTER_CLASS_TEXT:
                printAction(Static.TITLE_DIALOG_INFORMATION_PRINT, printerJob);
                break;
        }

        return object;
    }

    @Override
    protected void succeeded() {
        super.succeeded();

        switch (className) {
            case Static.FORM_PANE_CLASS_TEXT:

                SearchForm searchForm = (SearchForm) getValue();

                if (searchForm != null) {
                    List<String> nomTitulaireList = searchForm.getNomTitulaireList();
                    List<String> typeProcedureList = searchForm.getTypeProcedureList();
                    List<String> formePharmaceutiqueList = searchForm.getFormePharmaceutiqueList();
                    List<String> numeroAMMList = searchForm.getNumeroAMMList();
                    List<String> especeDestinationList = searchForm.getEspeceDestinationList();
                    List<String> substanceActiveList = searchForm.getSubstanceActiveList();
                    List<String> conditionDelivranceList = searchForm.getConditionDelivranceList();

                    String[] arrayNomTitulaire = nomTitulaireList.toArray(new String[0]);
                    fieldNomTitulaire.setArrayString(arrayNomTitulaire);
                    fieldNomTitulaire.setDisable(false);

                    String[] arrayNumeroAMM = numeroAMMList.toArray(new String[0]);
                    fieldNumeroAMM.setArrayString(arrayNumeroAMM);
                    fieldNumeroAMM.setDisable(false);

                    comboBoxSetItems(typeProcedureComboBox, typeProcedureList);
                    comboBoxSetItems(formePharmaceutiqueComboBox, formePharmaceutiqueList);
                    comboBoxSetItems(conditionDelivranceComboBox, conditionDelivranceList);

                    windowEspece.setModel(FXCollections.observableArrayList(especeDestinationList));

                    String[] arrayString = substanceActiveList.toArray(new String[0]);
                    windowSubstanceActive.setArrays(arrayString);
                    windowSubstanceActive.setFieldsDisable(false);
                }

                break;
            case Static.WINDOW_UPDATE_CLASS_TEXT:

                UpdateForm updateForm = (UpdateForm) getValue();

                if (updateForm != null) {
                    List<String> nomTitulaireList = updateForm.getNomTitulaireList();
                    List<String> typeProcedureList = updateForm.getTypeProcedureList();
                    List<String> statutAutorisationList = updateForm.getStatutAutorisationList();
                    List<String> formePharmaceutiqueList = updateForm.getFormePharmaceutiqueList();

                    String[] arrayNomTitulaire = nomTitulaireList.toArray(new String[0]);
                    fieldNomTitulaire.setArrayString(arrayNomTitulaire);
                    fieldNomTitulaire.setDisable(false);

                    comboBoxSetItems(typeProcedureComboBox, typeProcedureList);
                    comboBoxSetItems(statutAutorisationComboBox, statutAutorisationList);
                    comboBoxSetItems(formePharmaceutiqueComboBox, formePharmaceutiqueList);
                }

                break;
            case Static.FIELD_PANE_CLASS_TEXT:

                Map<String, Integer> nomIdMap = (Map<String, Integer>) getValue();

                if (nomIdMap != null) {
                    String[] arrayNom = Utils.nomIdMapToArrayNomSorted();

                    fieldNom.setArrayString(arrayNom);
                    windowDelete.setVariable(arrayNom);
                    clearEntriesInFiles();
                    fieldNom.setDisable(false);
                }

                break;
            case Static.WINDOW_DELETE_CLASS_TEXT:

                // Check if the autocompleteField name is identical to name of deleted Medicament
                // if true autocompleteField text and TableResult content are cleared
                boolean clear = fieldPane.setClear(id);

                if (clear) {
                    tableResult.clearEditorPane(Static.EMPTY_BODY_HTML);
                }

                TabPaneClass.updateDataInput();

                buttonDelete.setDisable(false);

                break;
            case Static.WINDOW_SAVE_UPDATE_TEXT:

                Medicament medicament = (Medicament) getValue();

                if (medicament != null) {
                    Controller.setMedicament(medicament);
                    tabPaneClass.updateData(medicament);

                    buttonUpdate.setDisable(false);
                    windowClass.hideWindow();
                }

                break;
            case Static.SEARCH_RESULT_CLASS_TEXT:

                List<String> resultList = (List<String>) getValue();

                timeline.stop();
                webViewBox.setBackground(Static.WEBVIEW_BOX_SLIGHTLY_LIGHT_BACKGROUNDS[Controller.getTheme()]);

                WebView webViewSearchResult = (WebView) webViewBox.getChildren().get(0);
                webViewSearchResult.setOpacity(1);

                if (resultList != null) {
                    String data = searchResultsGUI(resultList, medicamentSearch.getSkip());

                    webEngine.loadContent(data);
                }
                buttonSearch.setDisable(false);

                break;
            case Static.SEARCH_RESULT_SKIP_CLASS_TEXT:

                List<String> resultSkiplist = (List<String>) getValue();

//                System.out.println(medicamentSearch.getSkip());

                if (resultSkiplist != null) {
                    String data = searchResultsGUI(resultSkiplist, medicamentSearch.getSkip());

//                    System.out.println(data);

                    if (!data.isEmpty()) {
                        data = data.replace(Static.BACKSLASH_TEXT, Static.DOUBLE_BACKSLASH_TEXT);
                        String insert = Static.INSERT_JS_SEARCH_FIRST_PART + (medicamentSearch.getSkip() - 1) + Static.INSERT_JS_SECOND_PART + data + Static.INSERT_JS_END_PART;
                        webEngine.executeScript(insert);
                    }
                }

                break;
            case Static.TABLE_RESULT_CLASS_TEXT:

                String[] array = (String[]) getValue();

                timeline.stop();
                webViewBox.setBackground(Static.WEBVIEW_BOX_SLIGHTLY_LIGHT_BACKGROUNDS[Controller.getTheme()]);

                WebView webViewTableResult = (WebView) webViewBox.getChildren().get(0);
                webViewTableResult.setOpacity(1);

                if (array != null) {
                    webEngine.loadContent(array[0]);
                    tableResult.setArrayMedicamentData(array);

                    buttonUpdate.setDisable(false);
                    rcpToggleButton.setDisable(false);
                    favoriteToggleButton.setDisable(false);

                    addEntryToHistory(id, LocalDateTime.now());
                } else {
                    webEngine.loadContent(Static.AUCUNES_DONNEES);
                }

                rcpToggleButton.setSelected(false);

                boolean b = Utils.checkFavorite(id);
                favoriteToggleButton.setSelected(b);

                fieldNom.setDisable(false);

                break;
            case Static.SEARCH_RESULT_PRINTER_CLASS_TEXT:
            case Static.TABLE_RESULT_PRINTER_CLASS_TEXT:

                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void failed() {
        super.failed();

//        System.out.println(getException().getMessage());
        getException().printStackTrace();

        switch (className) {
            case Static.FORM_PANE_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);

                fieldNomTitulaire.setDisable(false);
                fieldNumeroAMM.setDisable(false);

                typeProcedureComboBox.setDisable(false);
                formePharmaceutiqueComboBox.setDisable(false);
                conditionDelivranceComboBox.setDisable(false);

                windowSubstanceActive.setFieldsDisable(false);

                break;
            case Static.WINDOW_UPDATE_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                fieldNomTitulaire.setDisable(false);

                break;
            case Static.FIELD_PANE_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                fieldNom.setDisable(false);

                break;
            case Static.WINDOW_DELETE_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);
                buttonDelete.setDisable(false);

                break;
            case Static.WINDOW_SAVE_UPDATE_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION_FILE_NOT_FOUND);
                buttonUpdate.setDisable(false);

                break;
            case Static.SEARCH_RESULT_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);

                timeline.stop();
                webViewBox.setBackground(Static.WEBVIEW_BOX_SLIGHTLY_LIGHT_BACKGROUNDS[Controller.getTheme()]);

                WebView webViewSearchResult = (WebView) webViewBox.getChildren().get(0);
                webViewSearchResult.setOpacity(1);

                buttonSearch.setDisable(false);

                break;
            case Static.SEARCH_RESULT_SKIP_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);

                // Set to true the Javascript variable appendContent, and set the skip variable of the Controller class
                webEngine.executeScript(Static.INSERT_JS_APPEND_TRUE);
                Controller.setSkip(Controller.getSkip() - Static.SKIP_HUNDRED);

                break;
            case Static.TABLE_RESULT_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.ERROR_INTERNET_CONNEXION);

                timeline.stop();
                webViewBox.setBackground(Static.WEBVIEW_BOX_SLIGHTLY_LIGHT_BACKGROUNDS[Controller.getTheme()]);

                WebView webViewTableResult = (WebView) webViewBox.getChildren().get(0);
                webViewTableResult.setOpacity(1);

                rcpToggleButton.setSelected(false);

                fieldNom.setDisable(false);

                break;
            case Static.SEARCH_RESULT_PRINTER_CLASS_TEXT:
            case Static.TABLE_RESULT_PRINTER_CLASS_TEXT:

                Utils.temporaryStatusMessage(Static.INTERRUPTION_IMPRESSION_TEXT);
                printerJob.cancel();

                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    /**
     * The method generates an HTML content containing the search results.
     *
     * @param list List of Strings
     * @param skip Number of values to skip
     * @return HTML content
     */
    private String searchResultsGUI(List<String> list, int skip) {
        return "";
    }

    /**
     * The objective of the method is to clear the id of the Medicament object that does no longer exist.
     */
    private void clearEntriesInFiles() {
    }

    private void addEntryToHistory(Integer id, LocalDateTime localDateTime) {
    }

    private void printAction(String title, PrinterJob printerJob) throws PrinterException {
    }

    private void comboBoxSetItems(ComboBoxClass comboBoxClass, List<String> list) {
    }
}
