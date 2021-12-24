package fr.medicamentvet.gui.tabpane;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.utils.Static;
import fr.medicamentvet.utils.TaskProducer;
import fr.medicamentvet.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

/**
 * The class shows the names of the medicaments after user completes the advanced search form.
 */
public class SearchResult extends VBox {

    private final ContextMenuClass contextMenuClass = new ContextMenuClass();

    private static String[] arrayFormInput;

    private final VBox webViewBox;
    private final WebView webView;
    private final WebEngine webEngine;
    private final Stage primaryStage;

    private MedicamentSearch medicamentSearch;

    public SearchResult(Stage primaryStage, FieldPane fieldPane, SingleSelectionModel<Tab> selectionModel) {

        this.primaryStage = primaryStage;

        webView = new WebView();
        webEngine = webView.getEngine();

        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[Controller.getTheme()]);

        webViewBox = new VBox(webView);
        webViewBox.getStyleClass().add(Static.WEBVIEW_BOX_CLASS_TEXT);

        getChildren().add(webViewBox);

        webView.setOnMousePressed(mouseEvent -> {
            webView.requestFocus();
            if (MouseButton.SECONDARY == mouseEvent.getButton()) {
                contextMenuClass.show(webView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
            } else {
                contextMenuClass.hide();
            }
        });

        // This listener triggers a requestFocus. Especially it helps to provoke the focus out event of the autocomplete field so that the popup becomes not visible.
        webView.setOnScroll(scrollEvent -> webView.requestFocus());

        webEngine.setOnAlert(stringWebEvent -> {
            int id = Integer.parseInt(stringWebEvent.getData());

            Map<Integer, String> idNomMap = Controller.getIdNomMap();
            String nom = idNomMap.get(id);

            selectionModel.select(Static.DONNEES_MEDICAMENT_TAB_INDEX);
            fieldPane.setFieldTextByNom(nom);
        });

        webEngine.getLoadWorker().stateProperty().addListener((observableValue, oldValue, newValue) -> {
//            if (newValue != Worker.State.SUCCEEDED) {return;}

            JSObject window = (JSObject) webEngine.executeScript(Static.WINDOW_TEXT);
            window.setMember(Static.SEARCH_RESULT_CLASS_TEXT, this);
        });
    }

    public ContextMenuClass getContextMenuClass() {
        return contextMenuClass;
    }

    /**
     * The purpose of the method is to insert HTML content to the WebView. Whenever user scrolls down new HTML content might be inserted.
     */
    public void insertHTML() {

        int skip = Controller.getSkip() + Static.SKIP_HUNDRED;
        medicamentSearch.setSkip(skip);
        Controller.setSkip(skip);

        System.out.println(medicamentSearch.getSkip());

        String aClassName = Static.SEARCH_RESULT_CLASS_TEXT + Static.SKIP_TEXT;

        StatusBar.setLabelMessage(Static.EMPTY_TEXT);
        TaskProducer taskProducer = new TaskProducer(aClassName, medicamentSearch, webEngine);
        new Thread(taskProducer).start();
    }

    /**
     * The method either performs a printing or generates a PDF file of the search results.
     *
     * @param print Boolean true to print a document, false to create a PDF file
     */
    public void generatePDFOrPrint(boolean print) {

        List<String> list = Controller.getSearchNomList();

        if (list != null) {

            List<String> medicamentNameList = changeList(list);
            String headerText = headerText(list);

            PDDocument document = null;

            try {
                document = new PDDocument();

                PDFont font = Utils.setPDFont(document, Static.FONT_PRINT);

                int headerWidth = (int) (Static.FONT_SIZE_NINE * font.getStringWidth(headerText) / 1000) + Static.X_MARGIN;

                int lineIndexText = 0;

                while (medicamentNameList.size() > 0) {

                    PDPage page = new PDPage();
                    document.addPage(page);

                    try (PDPageContentStream contents = new PDPageContentStream(document, page)) {

                        int headerHeight = 0;
                        int lineNumberInAddition = 1;

                        int formInputLineNumber = 0;

                        if (document.getNumberOfPages() == 1) {

                            int tx = Static.PAGE_WIDTH - headerWidth;
                            int ty = Static.PAGE_HEIGHT - Static.Y_MARGIN;

                            Utils.showSimpleText(headerText, contents, font, Static.FONT_SIZE_NINE, Static.FONT_SIZE_TWELVE, Static.COLOR_ANNOTATION, tx, ty);

                            headerHeight = Static.FONT_SIZE_NINE * 2;

                            ty = Static.PAGE_HEIGHT - Static.Y_MARGIN - headerHeight;

                            int lineNumberMaximum = ((Static.PAGE_HEIGHT - Static.Y_MARGIN) / (Static.FONT_SIZE_TWELVE + 2));

                            List<String> formInputList = new ArrayList<>(Arrays.asList(arrayFormInput));

                            formInputLineNumber = showResultTopText(formInputList, lineNumberMaximum, contents, font, ty);

                            lineNumberInAddition = formInputLineNumber * (-1);
                        }

                        int ty = Static.PAGE_HEIGHT - Static.Y_MARGIN - headerHeight - (formInputLineNumber + 1) * Static.FONT_SIZE_TWELVE;

                        int lineNumberMaximumPage = ((Static.PAGE_HEIGHT - Static.Y_MARGIN) / (Static.FONT_SIZE_FOURTEEN + 1)) + lineNumberInAddition;

                        int[] index = showResultText(medicamentNameList, lineNumberMaximumPage, contents, font, lineIndexText, ty);

                        lineIndexText = index[0];
                        int arrayIndex = index[1];

                        medicamentNameList.subList(0, arrayIndex).clear();
                    }
                }

                Utils.showFooterText(document, font);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (print) {
                String aClassName = Static.SEARCH_RESULT_CLASS_TEXT + Static.PRINTER_TEXT;
                Utils.printWithDialog(document, primaryStage, aClassName);
            } else {
                Utils.showFileChooserPDF(Static.FILENAME_RECHERCHE, document, primaryStage);
            }

        } else {
            Utils.showWarningMessage(Static.SEARCH_RESULT_CLASS_TEXT, print, primaryStage);
        }
    }

    /**
     * Based on the MedicamentSearch object parameter, the method achieves a background task to search the list of names of the medicaments.
     *
     * @param buttonSearch            Button search component
     * @param medicamentSearchInitial Initial MedicamentSearch object
     */
    public void setEditorPaneContent(Button buttonSearch, MedicamentSearch medicamentSearchInitial) {

        medicamentSearch = medicamentSearchInitial;

        List<String> formInputs = formInput(medicamentSearchInitial);
        arrayFormInput = formInputs.toArray(new String[0]);

        webEngine.loadContent(Static.EMPTY_BODY_HTML);

        Timeline timeline = Utils.waitAnimation(webView);
        webViewBox.setBackground(Static.WEBVIEW_BOX_LIGHT_BACKGROUNDS[Controller.getTheme()]);
        timeline.play();

        StatusBar.setLabelMessage(Static.EMPTY_TEXT);

        TaskProducer taskProducer = new TaskProducer(Static.SEARCH_RESULT_CLASS_TEXT, buttonSearch, medicamentSearch, webEngine, webViewBox, timeline);
        new Thread(taskProducer).start();
    }

    public String getContentSelection() {
        return (String) webEngine.executeScript(Static.WINDOW_GET_SELECTION_TOSTRING);
    }

    public void setTheme(int theme) {
        webEngine.setUserStyleSheetLocation(Static.URL_STYLE_SHEET_WEBVIEW[theme]);
        contextMenuClass.setTheme(theme);
    }

    /**
     * The method generates a list of Strings based on MedicamentSearch parameter used in the generated PDF and the printed documents.
     *
     * @param medicamentSearch MedicamentSearch object
     * @return List of Strings
     */
    private List<String> formInput(MedicamentSearch medicamentSearch) {
        return new ArrayList<>();
    }

    /**
     * The method returns a rearranged list of Strings.
     *
     * @param list List of Strings
     * @return List of Strings
     */
    private List<String> changeList(List<String> list) {
        return new ArrayList<>();
    }

    /**
     * The method returns the text of the header of the document.
     *
     * @param list List of Strings
     * @return String
     */
    private String headerText(List<String> list) {
        return "";
    }

    /**
     * The method adds text to the content stream for the first page: assuming that the content fits the first the page. The text is the parameters of the search form.
     *
     * @param list                  List of Strings
     * @param lineNumberMaximumPage Maximum number of lines on a page
     * @param contentStream         Content stream
     * @param font                  Font used for the page
     * @param ty                    Vertical position on the page
     * @return Integer number of lines
     */
    private int showResultTopText(List<String> list, int lineNumberMaximumPage, PDPageContentStream contentStream, PDFont font, int ty) {
        return 0;
    }

    /**
     * The purpose of the method is to add the results to content stream.
     *
     * @param list                  List of Strings
     * @param lineNumberMaximumPage Maximum number of lines on a page
     * @param contentStream         Content stream
     * @param font                  Font used for the page
     * @param lineIndexText         The line number for the String element of the list
     * @param ty                    Vertical position on the page
     * @return Integer array: lineIndexText and current index of the list
     */
    private int[] showResultText(List<String> list, int lineNumberMaximumPage, PDPageContentStream contentStream, PDFont font, int lineIndexText, int ty) {
        return new int[2];
    }
}
