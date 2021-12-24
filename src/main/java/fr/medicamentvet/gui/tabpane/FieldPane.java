package fr.medicamentvet.gui.tabpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.autocomplete.AutocompleteField;
import fr.medicamentvet.gui.windows.WindowDelete;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import java.util.Map;
import javafx.scene.layout.VBox;

/**
 * The class holds the AutocompleteField component, and completes tasks related to the AutocompleteField, the WindowDelete and the TableResult classes.
 */
public class FieldPane extends VBox {

    private final AutocompleteField fieldNom;
    private final TableResult tableResult;

    public FieldPane(TableResult tableResult) {

        this.tableResult = tableResult;

        fieldNom = new AutocompleteField(this, Static.NOM_DU_MEDICAMENT_TEXT, Static.NOM_DU_MEDICAMENT_TEXT);
        getChildren().addAll(fieldNom);
    }

    /**
     * After the deletion of the medicament, this method may set to null the Medicament object from the Controller class and may set the text of the AutocompleteField to empty.
     *
     * @param id id of the Medicament object
     * @return Boolean true means that the Medicament object and the text of the AutocompleteField have been initialized to null and empty respectively
     */
    public boolean setClear(int id) {
        boolean clear = false;

        Medicament medicament = Controller.getMedicament();

        if (medicament != null) {
            String nom = medicament.getNom();

            Map<String, Integer> nomIdMap = Controller.getNomIdMap();
            int idNom = nomIdMap.get(nom);

            if (id == idNom) {
                Controller.setMedicament(null);
                fieldNom.setText(Static.EMPTY_TEXT);
                clear = true;
            }
        }

        return clear;
    }

    public void setFieldTextByNom(String nom) {
        fieldNom.setAutocompleteFieldText(nom);
        getDataByNom(String.valueOf(nom));
    }

    public void setFieldTextAndRetrieveData(Medicament medicament) {
        String nom = medicament.getNom();
        fieldNom.setAutocompleteFieldText(nom);
        tableResult.setEditorPaneContent(medicament);
    }

    public void getDataByNom(String nom) {
        fieldNom.setDisable(true);

        Map<String, Integer> nomIdMap = Controller.getNomIdMap();

        int id = -1;
        if (nomIdMap.containsKey(nom)) {
            id = nomIdMap.get(nom);
        }

        tableResult.setEditorPaneContentById(id, fieldNom);
    }

    /**
     * The purpose of this method is to execute a task in a background thread, specially to set String array variable of AutocompleteField class. See the AutocompleteField and the WindowDelete classes for more information
     *
     * @param manual       Boolean true means that the background thread will always be executed
     * @param windowDelete WindowDelete component
     */
    public void setVariable(boolean manual, WindowDelete windowDelete) {

        if (fieldNom.getArrayString() == null || manual) {

            fieldNom.setDisable(true);

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);

            TaskProducer taskProducer = new TaskProducer(Static.FIELD_PANE_CLASS_TEXT, fieldNom, windowDelete);
            new Thread(taskProducer).start();
        }
    }
}
