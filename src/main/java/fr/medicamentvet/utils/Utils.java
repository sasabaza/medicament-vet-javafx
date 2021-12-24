package fr.medicamentvet.utils;

import fr.medicamentvet.controller.Controller;
import fr.medicamentvet.gui.StatusBar;
import fr.medicamentvet.gui.windows.WindowWarning;
import java.awt.Color;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.print.PrintService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.printing.PDFPageable;

/**
 * The class contains static utility methods.
 */
public final class Utils {

    private Utils() {
        super();
    }

    /**
     * The method formats a LocalDate to a String using specific formatter.
     *
     * @param date LocalDate
     * @return String
     */
    public static String localDateToStringDateFR(LocalDate date) {
        return date.format(Static.DATE_TIME_FORMATTER_FR_1);
    }

    /**
     * The method parses the favorite file and returns array of the ids.
     *
     * @return Array of ids
     */
    public static Integer[] readAllFileFavorite() {

        Integer[] arrayInteger = null;

        try (Stream<String> stringStream = Files.lines(Static.PATH_FILE_FAVORITE, StandardCharsets.UTF_8)) {
            arrayInteger = stringStream.map(Integer::valueOf).toArray(Integer[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayInteger;
    }

    /**
     * The method checks whether true or false the medicament has been added to the favorite list.
     *
     * @param id id of the Medicament object
     * @return Boolean true or false the medicament is in the favorite list
     */
    public static boolean checkFavorite(Integer id) {

        Integer[] arrayInteger = readAllFileFavorite();

        // Sort array to use binary search
        Arrays.sort(arrayInteger);

        int[] arrayInt = customBinarySearchInteger(arrayInteger, id);

        return arrayInt[0] != -1;
    }

    /**
     * The method loads a custom font to the PDDocument document object.
     *
     * @param document PDDocument object
     * @param fileName File name of the font
     * @return PDFont font
     */
    public static PDFont setPDFont(PDDocument document, String fileName) {
        PDFont font = null;

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            font = PDType0Font.load(document, fileInputStream, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return font;
    }

    /**
     * The method displays a window when the printing or the creation of a PDF file is not possible.
     *
     * @param className Class name
     * @param print     Boolean true if the process is to print, false if the process is to create a PDF file
     * @param stage     Stage of the parent component
     */
    public static void showWarningMessage(String className, boolean print, Stage stage) {
        String message = null;
        String title = null;

        if (className.equals(Static.SEARCH_RESULT_CLASS_TEXT)) {
            title = Static.TITLE_DIALOG_RECHERCHE_PRINT;
            if (print) {
                message = Static.MESSAGE_ERREUR_PRINT;
            } else {
                message = Static.MESSAGE_ERREUR_PDF;
            }
        }

        if (className.equals(Static.TABLE_RESULT_CLASS_TEXT)) {
            title = Static.TITLE_DIALOG_INFORMATION_PRINT;
            if (print) {
                message = Static.MESSAGE_ERREUR_PRINT;
            } else {
                message = Static.MESSAGE_ERREUR_PDF;
            }
        }

        WindowWarning windowWarning = new WindowWarning(stage, title, message);
        windowWarning.showWindow();
    }

    /**
     * The method shows a custom text at the bottom of each page of the PDDocument object.
     *
     * @param document PDDocument object
     * @param font     Font of the text
     */
    public static void showFooterText(PDDocument document, PDFont font) {
        int numberPages = document.getNumberOfPages();

        for (int i = 0; i < numberPages; i++) {

            Date date = new Date();

            String footerText = Static.SIMPLE_DATE_FORMAT_FR.format(date);

            int indexPage = i + 1;

            footerText = footerText + Static.FOOTER_PAGE_TEXT + indexPage + Static.FORWARD_SLASH + numberPages;

            int footerWidth = 0;
            try {
                footerWidth = (int) (Static.FONT_SIZE_NINE * font.getStringWidth(footerText) / 1000) / 2;
            } catch (IOException e) {
                e.printStackTrace();
            }

            PDPage page = document.getPage(i);

            int tx = Static.PAGE_WIDTH_DIVIDED_BY_2 - footerWidth;

            try (PDPageContentStream contents = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false, false)) {

                showSimpleText(footerText, contents, font, Static.FONT_SIZE_NINE, Static.FONT_SIZE_TWELVE, Static.COLOR_ANNOTATION, tx, Static.Y_MARGIN);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method performs the printing process.
     *
     * @param document  PDDocument object
     * @param stage     Stage of the component
     * @param className Class name
     */
    public static void printWithDialog(PDDocument document, Stage stage, String className) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        PrintService printService = printerJob.getPrintService();

        if (printService != null) {
            printerJob.setPageable(new PDFPageable(document));

            StatusBar.setLabelMessage(Static.EMPTY_TEXT);

            TaskProducer taskProducer = new TaskProducer(className, printerJob, document);
            new Thread(taskProducer).start();
        } else {
            try {
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            WindowWarning windowWarning = new WindowWarning(stage, Static.WINDOW_WARNING_TITLE_ERROR_PRINT_SERVICE_TEXT, Static.ERROR_MESSAGE_PRINT_SERVICE_NOT_FOUND);
            windowWarning.showWindow();
        }
    }

    /**
     * The method puts a text at a specific horizontal and vertical position to the content stream.
     *
     * @param text            String text
     * @param contentStream   Content stream
     * @param font            Font of the text
     * @param fontSize        Font size of the text
     * @param fontSizeLeading Space between lines
     * @param color           Color of the text
     * @param tx              Horizontal position
     * @param ty              Vertical position
     */
    public static void showSimpleText(String text, PDPageContentStream contentStream, PDFont font, int fontSize, int fontSizeLeading, int color, int tx, int ty) {
        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(tx, ty);
            contentStream.setLeading(fontSizeLeading);
            contentStream.setNonStrokingColor(new Color(color, color, color));
            contentStream.setFont(font, fontSize);
            contentStream.showText(text);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method resizes an image to a maximum length and returns an Image.
     *
     * @param fileName          Name of the image file
     * @param buttonNext        Button to continue to the next window
     * @param buttonReturn      Button to either cancel, or return to the previous window
     * @param buttonAddImage    Button to add an image
     * @param buttonRemoveImage Button to remove an image
     * @param hashSetError      Set of errors
     * @return Image
     */
    public static Image imageResize(String fileName, Button buttonNext, Button buttonReturn, Button buttonAddImage, Button buttonRemoveImage, LinkedHashSet<String> hashSetError) {

        Image image = null;

        URL url;
        if (fileName.startsWith(Static.STARTS_WITH_HTTP)) {
            try {
                url = new URL(fileName);

                try (Socket ignored = new Socket("1.1.1.1", 443)) {

                    image = newImage(url);
                } catch (UnknownHostException | NoRouteToHostException | ConnectException e) {
                    buttonNext.setDisable(true);
                    buttonReturn.setDisable(true);
                    buttonAddImage.setDisable(true);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setDisable(true);
                    }

                    hashSetError.add(Static.CONNECTION_FAILED_RETRY);

                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                buttonAddImage.setDisable(false);

                if (buttonRemoveImage != null) {
                    buttonRemoveImage.setDisable(true);
                }
                e.printStackTrace();
            }
        } else {
            String filePath = null;

            if (Static.OS_NAME.equals(Static.OS_NAME_LINUX_TEXT)) {
                filePath = Static.FILE_PATH + fileName;
            }
            if (Static.OS_NAME.equals(Static.OS_NAME_WINDOWS_10_TEXT)) {
                fileName = fileName.replace(Static.BACKSLASH_DOUBLE, Static.FORWARD_SLASH);
                filePath = Static.FILE_PATH_FORWARD_SLASH + fileName;
            }

            if (filePath != null) {
                try {
                    url = new URL(filePath);

                    image = newImage(url);
                } catch (MalformedURLException e) {
                    buttonAddImage.setDisable(false);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setDisable(true);
                    }
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    buttonAddImage.setDisable(false);

                    if (buttonRemoveImage != null) {
                        buttonRemoveImage.setDisable(true);
                    }

                    String[] strings = Static.SEPARATOR_3.split(fileName);
                    String file = strings[strings.length - 1];

                    hashSetError.add(Static.ERROR_MESSAGE_FILE_NOT_FOUND_1 + file + Static.ERROR_MESSAGE_FILE_NOT_FOUND_2);

                    e.printStackTrace();
                }
            }
        }

        return image;
    }

    /**
     * The method takes nomIdMap object from Controller class and returns sorted array of names of the medicaments.
     *
     * @return Array of names of the medicaments
     */
    public static String[] nomIdMapToArrayNomSorted() {

        Map<String, Integer> nomIdMap = Controller.getNomIdMap();
        List<String> list = new ArrayList<>(nomIdMap.keySet());

        // Sort according to a specific comparator
        list.sort(Static.COLLATOR);

        return list.toArray(new String[0]);
    }

    /**
     * The method may enable or disable Button control from the list of components making sure that there is at least one row of components.
     *
     * @param observableList List of Nodes
     * @param disable        Boolean enable or disable button control
     */
    public static void buttonEnable(ObservableList<Node> observableList, boolean disable) {
        if (observableList.size() == 1) {
            for (Node component : observableList) {
                HBox hBox = (HBox) component;

                ObservableList<Node> nodes = hBox.getChildren();

                for (Node node : nodes) {
                    if (node.getClass().getSimpleName().equals(Static.BUTTON_CLASS_TEXT)) {
                        node.setDisable(disable);
                    }
                }
            }
        }
    }

    /**
     * The purpose of the method is to create a Timeline based on the WebView opacity property.
     *
     * @param webView WebView
     * @return Timeline
     */
    public static Timeline waitAnimation(WebView webView) {

        KeyValue initKeyValue = new KeyValue(webView.opacityProperty(), Static.OPACITY_ZERO);
        KeyFrame initFrame = new KeyFrame(Duration.ZERO, initKeyValue);
        KeyValue midKeyValue = new KeyValue(webView.opacityProperty(), Static.OPACITY_ONE);
        KeyFrame midFrame = new KeyFrame(Duration.millis(Static.DURATION_TIMELINE_TWO_HUNDRED), midKeyValue);
        KeyValue endKeyValue = new KeyValue(webView.opacityProperty(), Static.OPACITY_ZERO);
        KeyFrame endFrame = new KeyFrame(Duration.millis(Static.DURATION_TIMELINE_FOUR_HUNDRED), endKeyValue);
        Timeline timeline = new Timeline(initFrame, midFrame, endFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return timeline;
    }

    /**
     * The method saves a PDF file using PDFBox Apache plugin.
     *
     * @param fileName File name
     * @param document PDDocument object
     * @param stage    Stage of the parent component
     */
    public static void showFileChooserPDF(String fileName, PDDocument document, Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(Static.USER_DIRECTORY));
        fileChooser.setTitle(Static.DIALOG_TITLE_PDF_TEXT);

        String currentDate = LocalDate.now().format(Static.DATE_TIME_FORMATTER_FR_2);
        String initialFileName = fileName + Static.DASH_TEXT + currentDate + Static.POINT_TEXT + Static.PDF_EXTENSION_TEXT;
        fileChooser.setInitialFileName(initialFileName);

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(Static.PDF_EXTENSION_DESCRIPTION_TEXT, Static.STAR_TEXT + Static.POINT_TEXT + Static.PDF_EXTENSION_TEXT));

        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                document.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The aim of this method is to allow user to choose an image from user's directory.
     *
     * @param stage Stage of the parent component
     * @return String file name
     */
    public static String showFileChooserImage(Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(Static.USER_DIRECTORY));
        fileChooser.setTitle(Static.DIALOG_TITLE_IMAGE_TEXT);

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(Static.JPG_EXTENSION_DESCRIPTION_TEXT, Static.STAR_TEXT + Static.POINT_TEXT + Static.JPG_EXTENSION_TEXT), new FileChooser.ExtensionFilter(Static.JPEG_EXTENSION_DESCRIPTION_TEXT, Static.STAR_TEXT + Static.POINT_TEXT + Static.JPEG_EXTENSION_TEXT), new FileChooser.ExtensionFilter(Static.PNG_EXTENSION_DESCRIPTION_TEXT, Static.STAR_TEXT + Static.POINT_TEXT + Static.PNG_EXTENSION_TEXT));

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String name = file.getName();

            if (!Static.PATTERN_IMAGE.matcher(name).matches()) {
                WindowWarning windowWarning = new WindowWarning(stage, Static.WINDOW_WARNING_TITLE_INCORRECT_FILE_TEXT, Static.ERROR_MESSAGE_INCORRECT_FILE_TEXT);
                windowWarning.showWindow();

                return null;
            } else {
                if (!name.toLowerCase().endsWith(Static.POINT_TEXT + Static.JPG_EXTENSION_TEXT) && !name.toLowerCase().endsWith(Static.POINT_TEXT + Static.JPEG_EXTENSION_TEXT) && !name.toLowerCase().endsWith(Static.POINT_TEXT + Static.PNG_EXTENSION_TEXT)) {
                    WindowWarning windowWarning = new WindowWarning(stage, Static.WINDOW_WARNING_TITLE_INCORRECT_EXTENSION_TEXT, Static.ERROR_MESSAGE_INCORRECT_EXTENSION_TEXT);
                    windowWarning.showWindow();

                    return null;
                } else {
                    return file.toString();
                }
            }
        } else {
            return null;
        }
    }

    /**
     * The purpose of the method is to show a temporary message on the status bar.
     *
     * @param message Message to display on the status bar
     */
    public static void temporaryStatusMessage(String message) {
        StatusBar.setLabelMessage(message);

        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(Static.DELAY_15), actionEvent -> StatusBar.setLabelMessage(Static.EMPTY_TEXT)));
        timer.setCycleCount(1);
        timer.play();
    }

    /**
     * The method generates an Image from the URL of the image file.
     *
     * @param url URL of the image file
     * @return Image
     */
    private static Image newImage(URL url) throws FileNotFoundException {
        return new Image("");
    }

    /**
     * The method searches an Integer in an array, and returns whether true or false the Integer is found and the position in the array.
     *
     * @param arrayInteger Array of ids
     * @param integer      Integer id
     * @return Integer array. If id is found the array contains the position of the element. If id is not found, first element of the array is -1 and the second element is the position where the element should be added.
     */
    private static int[] customBinarySearchInteger(Integer[] arrayInteger, Integer integer) {
        return new int[2];
    }
}
