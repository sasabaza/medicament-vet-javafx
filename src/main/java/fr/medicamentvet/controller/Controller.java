package fr.medicamentvet.controller;

import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.entities.SearchForm;
import fr.medicamentvet.entities.UpdateForm;
import fr.medicamentvet.services.Services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The purpose of the class is to save main objects such as Medicament and UpdateForm objects, Map and arrays.
 */
public final class Controller {

    private static String[] arrayLabel;
    private static String[] arrayString;
    private static String[][] arrayString2D;
    private static List<String> searchNomList;

    private static Map<String, Integer> nomIdMap;
    private static Map<Integer, String> idNomMap;
    private static Medicament medicament;
    private static String nomMedicament;
    private static UpdateForm updateForm;
    private static int theme;

    private static int skip;

    /**
     * The method gets all nom and id (K,V) associated with Medicament object, initializes nomIdMap and idNomMap objects.
     *
     * @return {@code HashMap<String, Integer>}
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static Map<String, Integer> getAllMedicamentsNomId() throws IOException, InterruptedException {

        nomIdMap = Services.getAllMedicamentsNomId();

        idNomMap = nomIdMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        return nomIdMap;
    }

    /**
     * The method gets the inputs for SearchForm object from Services class.
     *
     * @return SearchForm object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static SearchForm getSearchFormInputs() throws IOException, InterruptedException {
        return Services.getSearchFormInputs();
    }

    /**
     * The method gets the inputs for UpdateForm object from Services class, and initializes updateForm object.
     *
     * @return UpdateForm object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static UpdateForm getUpdateFormInputs() throws IOException, InterruptedException {
        updateForm = Services.getUpdateFormInputs();

        return updateForm;
    }

    /**
     * The method returns a String array given the id of a Medicament object.
     *
     * @param id id of the Medicament object
     * @return String array HTML data used to set the EditorPane
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static String[] getMedicamentById(int id) throws IOException, InterruptedException {
        medicament = Services.getMedicamentById(id);

        if (medicament != null) {
            nomMedicament = medicament.getNom();
        }

        return createArray(medicament);
    }

    /**
     * The method updates the data of a Medicament object.
     *
     * @param medicament Medicament object
     * @return Medicament object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static Medicament updateMedicament(Medicament medicament) throws IOException, InterruptedException {
        return Services.updateMedicament(medicament);
    }

    /**
     * The method deletes all data associated with Medicament object, the files, and the directories.
     *
     * @param id id of the Medicament object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static void deleteMedicamentById(int id) throws IOException, InterruptedException {
        Services.deleteMedicamentById(id);
    }

    /**
     * The method performs a search with the MedicamentSearch data, and returns a list of names of the medicaments.
     *
     * @param medicamentSearch MedicamentSearch object
     * @return List of names of the medicaments
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static List<String> searchMedicamentNames(MedicamentSearch medicamentSearch) throws IOException, InterruptedException {
        List<String> list = Services.searchMedicamentNames(medicamentSearch);

        if (medicamentSearch.getSkip() == 0) {
            searchNomList = new ArrayList<>(list);
        } else {
            searchNomList.addAll(list);
        }

        return list;
    }

    /**
     * The method creates an array of size two employed to set the WebView.
     *
     * @param medicament Medicament object
     * @return String array HTML data used to set the WebView
     */
    public static String[] createArray(Medicament medicament) {
        if (medicament != null) {
            String[][] arrayString2DGUI = medicamentToStringGUI(medicament);

            arrayString = medicamentToStringData(medicament);

            String[] arrayStringGUI = arrayString2DGUI[0];
            arrayLabel = arrayString2DGUI[1];

            String[] data = new String[2];

            data[0] = generationTableGUI(arrayStringGUI, arrayLabel);

            Rcp rcp = medicament.getRcp();

            if (rcp != null) {
                arrayString2D = rcp.toArrayString2D();
            } else {
                arrayString2D = null;
            }

            data[1] = arrayString2DToStringGUI(arrayString2D);

            return data;
        } else {
            arrayString = null;
            arrayLabel = null;
            arrayString2D = null;
            return null;
        }
    }

    public static List<String> getSearchNomList() {
        return searchNomList;
    }

    public static Map<String, Integer> getNomIdMap() {
        return nomIdMap;
    }

    public static Map<Integer, String> getIdNomMap() {
        return idNomMap;
    }

    public static Medicament getMedicament() {
        return medicament;
    }

    public static void setMedicament(Medicament medicament) {
        Controller.medicament = medicament;
    }

    public static UpdateForm getUpdateForm() {
        return updateForm;
    }

    public static int getTheme() {
        return theme;
    }

    public static void setTheme(int theme) {
        Controller.theme = theme;
    }

    public static int getSkip() {
        return skip;
    }

    public static void setSkip(int skip) {
        Controller.skip = skip;
    }

    /**
     * The method transforms a Medicament object to a two-dimensional String array.
     *
     * @param medicament Medicament object
     * @return Two-dimensional String array: first array is the data, other one is the labels.
     */
    private static String[][] medicamentToStringGUI(Medicament medicament) {
        return new String[2][];
    }

    /**
     * The method transforms a Medicament object to a String array.
     *
     * @param medicament Medicament object
     * @return String array
     */
    private static String[] medicamentToStringData(Medicament medicament) {
        return new String[17];
    }

    /**
     * The method takes two arrays and generates an HTML table content.
     *
     * @param arrayData  Array of data
     * @param arrayLabel Array of labels
     * @return String HTML table
     */
    private static String generationTableGUI(String[] arrayData, String[] arrayLabel) {
        return "";
    }

    /**
     * The method returns HTML content based on two-dimensional String array parameter.
     *
     * @param arrayString2D Two-dimensional String array
     * @return String HTML content
     */
    private static String arrayString2DToStringGUI(String[][] arrayString2D) {
        return "";
    }
}
