package fr.medicamentvet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.medicamentvet.gui.simple.ButtonClass;
import fr.medicamentvet.gui.simple.ToggleButtonClass;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * The class contains most of the static variables.
 */
public final class Static {

    // ApplicationClass, TabPaneClass
    /******************************************************************************************************************/

    public static final String TITLE_APPLICATION = "Médicament vétérinaire";
    public static final double STAGE_WIDTH = 730;
    public static final double STAGE_HEIGHT = 680;
    private static final String URL_STYLE_SHEET_DARK = Objects.requireNonNull(Static.class.getResource("/css/dark/style.css")).toExternalForm();
    private static final String URL_STYLE_SHEET_DEFAULT = Objects.requireNonNull(Static.class.getResource("/css/default/style.css")).toExternalForm();
    public static final String[] URL_STYLE_SHEET = {URL_STYLE_SHEET_DEFAULT, URL_STYLE_SHEET_DARK};

    public static final String PATH_MEDICAMENT_VET = System.getProperty("user.home") + "/Medicament-Vet/";
    private static final String FILE_NAME_THEME = "theme.txt";
    private static final String FILE_NAME_FAVORITE = "favorite.txt";
    public static final Path PATH_FILE_THEME = Paths.get(PATH_MEDICAMENT_VET + FILE_NAME_THEME);
    public static final Path PATH_FILE_FAVORITE = Paths.get(PATH_MEDICAMENT_VET + FILE_NAME_FAVORITE);

    public static final String EXCEPTION_TEXT = "EXCEPTION: ";

    // Controller
    /******************************************************************************************************************/

    public static final String ESPECE_DESTINATION_PLURIEL_TEXT = "Espèces de destination";
    public static final String SUBSTANCE_ACTIVE_PLURIEL_TEXT = "Substances actives";
    public static final String CONDITION_DELIVRANCE_SINGULIER_TEXT = "Condition de délivrance";
    public static final String CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT = "Conditionnement primaire";
    public static final String CODE_ATCVET_SINGULIER_TEXT = "Code ATCVET";
    private static final String COLON_SPACE_TEXT = ": ";
    public static final String PATTERN_SPACE = " ";
    public static final String PATTERN_NEWLINE = "\n";
    public static final String IMAGE_TEXT = "Image";

    // Medicament, Rcp
    /******************************************************************************************************************/

    public static final String PATTERN_AMP = "&";
    public static final String SPACE_AMP_AMP_SPACE_TEXT = PATTERN_SPACE + PATTERN_AMP + PATTERN_AMP + PATTERN_SPACE;

    // MainPane
    /******************************************************************************************************************/

    public static final double DELAY_15 = 15;

    // TabPaneClass
    /******************************************************************************************************************/

    public static final double WINDOW_FAVORITE_STAGE_WIDTH = 550;
    public static final double WINDOW_FAVORITE_STAGE_HEIGHT = 310;
    public static final double WINDOW_HISTORY_STAGE_WIDTH = 650;
    public static final double WINDOW_HISTORY_STAGE_HEIGHT = 400;
    public static final double WINDOW_DELETE_STAGE_WIDTH = 566;
    public static final double WINDOW_DELETE_STAGE_HEIGHT = 120;
    public static final double WINDOW_ABOUT_STAGE_WIDTH = 280;
    public static final double WINDOW_ABOUT_STAGE_HEIGHT = 160;

    public static final String FAVORIS_TEXT = "Favoris";
    public static final String HISTORIQUE_TEXT = "Historique";
    public static final String SUPPRIMER_MEDICAMENT_TEXT = "Supprimer un médicament";
    public static final String A_PROPOS_TEXT = "À propos";
    public static final String DONNEES_MEDICAMENT_TEXT = "Données médicament";
    public static final String RECHERCHE_AVANCEE_TEXT = "Recherche avancée";
    public static final String FIELD_PANE_AND_TABLE_RESULT_CLASS_TEXT = "field-pane-and-table-result";
    public static final String FORM_PANE_AND_SEARCH_RESULT_CLASS_TEXT = "form-pane-and-search-result";

    // ToolBarClass
    /******************************************************************************************************************/

    private static final String IMAGE_PATH_PRINT_TOOLBAR_DEFAULT = "/icons/toolbar/default/print.png";
    private static final String IMAGE_PATH_CREATE_PDF_TOOLBAR_DEFAULT = "/icons/toolbar/default/pdf.png";
    private static final String IMAGE_PATH_UPDATE_TOOLBAR_DEFAULT = "/icons/toolbar/default/update.png";
    private static final String IMAGE_PATH_FAVORITE_TOOLBAR_DEFAULT = "/icons/toolbar/default/favorite.png";
    private static final String IMAGE_PATH_HISTORY_TOOLBAR_DEFAULT = "/icons/toolbar/default/history.png";
    private static final String IMAGE_PATH_DELETE_TOOLBAR_DEFAULT = "/icons/toolbar/default/delete.png";

    private static final String IMAGE_URL_PRINT_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_PRINT_TOOLBAR_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_CREATE_PDF_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_CREATE_PDF_TOOLBAR_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_UPDATE_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_UPDATE_TOOLBAR_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_FAVORITE_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_FAVORITE_TOOLBAR_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_HISTORY_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_HISTORY_TOOLBAR_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_DELETE_TOOLBAR_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_DELETE_TOOLBAR_DEFAULT)).toExternalForm();

    public static final Image IMAGE_PRINT_TOOLBAR_DEFAULT = new Image(IMAGE_URL_PRINT_TOOLBAR_DEFAULT);
    public static final Image IMAGE_CREATE_PDF_TOOLBAR_DEFAULT = new Image(IMAGE_URL_CREATE_PDF_TOOLBAR_DEFAULT);
    public static final Image IMAGE_UPDATE_TOOLBAR_DEFAULT = new Image(IMAGE_URL_UPDATE_TOOLBAR_DEFAULT);
    public static final Image IMAGE_FAVORITE_TOOLBAR_DEFAULT = new Image(IMAGE_URL_FAVORITE_TOOLBAR_DEFAULT);
    public static final Image IMAGE_HISTORY_TOOLBAR_DEFAULT = new Image(IMAGE_URL_HISTORY_TOOLBAR_DEFAULT);
    public static final Image IMAGE_DELETE_TOOLBAR_DEFAULT = new Image(IMAGE_URL_DELETE_TOOLBAR_DEFAULT);

    private static final String IMAGE_PATH_PRINT_TOOLBAR_DARK = "/icons/toolbar/dark/print.png";
    private static final String IMAGE_PATH_CREATE_PDF_TOOLBAR_DARK = "/icons/toolbar/dark/pdf.png";
    private static final String IMAGE_PATH_UPDATE_TOOLBAR_DARK = "/icons/toolbar/dark/update.png";
    private static final String IMAGE_PATH_FAVORITE_TOOLBAR_DARK = "/icons/toolbar/dark/favorite.png";
    private static final String IMAGE_PATH_HISTORY_TOOLBAR_DARK = "/icons/toolbar/dark/history.png";
    private static final String IMAGE_PATH_DELETE_TOOLBAR_DARK = "/icons/toolbar/dark/delete.png";

    private static final String IMAGE_URL_PRINT_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_PRINT_TOOLBAR_DARK)).toExternalForm();
    private static final String IMAGE_URL_CREATE_PDF_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_CREATE_PDF_TOOLBAR_DARK)).toExternalForm();
    private static final String IMAGE_URL_UPDATE_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_UPDATE_TOOLBAR_DARK)).toExternalForm();
    private static final String IMAGE_URL_FAVORITE_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_FAVORITE_TOOLBAR_DARK)).toExternalForm();
    private static final String IMAGE_URL_HISTORY_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_HISTORY_TOOLBAR_DARK)).toExternalForm();
    private static final String IMAGE_URL_DELETE_TOOLBAR_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_DELETE_TOOLBAR_DARK)).toExternalForm();

    public static final Image IMAGE_PRINT_TOOLBAR_DARK = new Image(IMAGE_URL_PRINT_TOOLBAR_DARK);
    public static final Image IMAGE_CREATE_PDF_TOOLBAR_DARK = new Image(IMAGE_URL_CREATE_PDF_TOOLBAR_DARK);
    public static final Image IMAGE_UPDATE_TOOLBAR_DARK = new Image(IMAGE_URL_UPDATE_TOOLBAR_DARK);
    public static final Image IMAGE_FAVORITE_TOOLBAR_DARK = new Image(IMAGE_URL_FAVORITE_TOOLBAR_DARK);
    public static final Image IMAGE_HISTORY_TOOLBAR_DARK = new Image(IMAGE_URL_HISTORY_TOOLBAR_DARK);
    public static final Image IMAGE_DELETE_TOOLBAR_DARK = new Image(IMAGE_URL_DELETE_TOOLBAR_DARK);

    public static final Image[] IMAGE_PRINT_TOOLBAR = {IMAGE_PRINT_TOOLBAR_DEFAULT, IMAGE_PRINT_TOOLBAR_DARK};
    public static final Image[] IMAGE_CREATE_PDF_TOOLBAR = {IMAGE_CREATE_PDF_TOOLBAR_DEFAULT, IMAGE_CREATE_PDF_TOOLBAR_DARK};
    public static final Image[] IMAGE_UPDATE_TOOLBAR = {IMAGE_UPDATE_TOOLBAR_DEFAULT, IMAGE_UPDATE_TOOLBAR_DARK};
    public static final Image[] IMAGE_FAVORITE_TOOLBAR = {IMAGE_FAVORITE_TOOLBAR_DEFAULT, IMAGE_FAVORITE_TOOLBAR_DARK};
    public static final Image[] IMAGE_HISTORY_TOOLBAR = {IMAGE_HISTORY_TOOLBAR_DEFAULT, IMAGE_HISTORY_TOOLBAR_DARK};
    public static final Image[] IMAGE_DELETE_TOOLBAR = {IMAGE_DELETE_TOOLBAR_DEFAULT, IMAGE_DELETE_TOOLBAR_DARK};

    // AutocompleteField, TextFieldClass
    /******************************************************************************************************************/

    public static final String ERROR_FIELD_CLASS_TEXT = "error-field";

    // AutocompletePopup
    /******************************************************************************************************************/

    // Maximum number of results to show
    public static final int ROW_MAXIMUM_NUMBER = 8;
    public static final double CELL_SIZE = 23;
    public static final String FIELD_PANE_CLASS_TEXT = "FieldPane";
    public static final String AUTOCOMPLETE_POPUP_CLASS_TEXT = "AutocompletePopup";

    // MenuBarClass, ContextMenuClass
    /******************************************************************************************************************/

    public static final String MENU_ITEM_IMPRIMER_TEXT = "_Imprimer...";
    public static final String MENU_ITEM_CREER_PDF_TEXT = "Créer _PDF...";
    public static final String MENU_ITEM_MISE_A_JOUR_TEXT = "_Mise à jour";
    public static final String MENU_ITEM_FAVORIS_TEXT = "_Favoris";
    public static final String MENU_ITEM_HISTORIQUE_TEXT = "_Historique";
    public static final String MENU_ITEM_SUPPRIMER_MEDICAMENT_TEXT = "_Supprimer un médicament";
    public static final String MENU_ITEM_QUITTER_TEXT = "_Quitter";
    public static final String MENU_ITEM_A_PROPOS_TEXT = "À _propos";
    public static final String MENU_FICHIER_TEXT = "_Fichier";
    public static final String MENU_AIDE_TEXT = "_Aide";
    public static final String MENU_THEME_TEXT = "_Thème";
    public static final String MENU_ITEM_LIGHT_TEXT = "_Clair";
    public static final String MENU_ITEM_DARK_TEXT = "_Sombre";

    public static final String COPIER_TEXT = "Copier";
    public static final String IMPRIMER_TEXT = "Imprimer";
    public static final String CREER_PDF_TEXT = "Créer PDF";

    private static final String IMAGE_PATH_PRINT_MENU_DEFAULT = "/icons/menu/default/print.png";
    private static final String IMAGE_PATH_CREATE_PDF_MENU_DEFAULT = "/icons/menu/default/pdf.png";
    private static final String IMAGE_PATH_UPDATE_MENU_DEFAULT = "/icons/menu/default/update.png";
    private static final String IMAGE_PATH_FAVORITE_MENU_DEFAULT = "/icons/menu/default/favorite.png";
    private static final String IMAGE_PATH_HISTORY_MENU_DEFAULT = "/icons/menu/default/history.png";
    private static final String IMAGE_PATH_DELETE_MENU_DEFAULT = "/icons/menu/default/delete.png";
    private static final String IMAGE_PATH_COPY_MENU_DEFAULT = "/icons/menu/default/copy.png";
    private static final String IMAGE_PATH_EXIT_MENU_DEFAULT = "/icons/menu/default/exit.png";
    private static final String IMAGE_PATH_ABOUT_MENU_DEFAULT = "/icons/menu/default/about.png";

    private static final String IMAGE_URL_PRINT_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_PRINT_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_CREATE_PDF_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_CREATE_PDF_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_UPDATE_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_UPDATE_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_FAVORITE_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_FAVORITE_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_HISTORY_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_HISTORY_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_DELETE_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_DELETE_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_COPY_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_COPY_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_EXIT_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_EXIT_MENU_DEFAULT)).toExternalForm();
    private static final String IMAGE_URL_ABOUT_MENU_DEFAULT = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_ABOUT_MENU_DEFAULT)).toExternalForm();

    public static final Image IMAGE_PRINT_MENU_DEFAULT = new Image(IMAGE_URL_PRINT_MENU_DEFAULT);
    public static final Image IMAGE_CREATE_PDF_MENU_DEFAULT = new Image(IMAGE_URL_CREATE_PDF_MENU_DEFAULT);
    public static final Image IMAGE_UPDATE_MENU_DEFAULT = new Image(IMAGE_URL_UPDATE_MENU_DEFAULT);
    public static final Image IMAGE_FAVORITE_MENU_DEFAULT = new Image(IMAGE_URL_FAVORITE_MENU_DEFAULT);
    public static final Image IMAGE_HISTORY_MENU_DEFAULT = new Image(IMAGE_URL_HISTORY_MENU_DEFAULT);
    public static final Image IMAGE_DELETE_MENU_DEFAULT = new Image(IMAGE_URL_DELETE_MENU_DEFAULT);
    public static final Image IMAGE_COPY_MENU_DEFAULT = new Image(IMAGE_URL_COPY_MENU_DEFAULT);
    public static final Image IMAGE_EXIT_MENU_DEFAULT = new Image(IMAGE_URL_EXIT_MENU_DEFAULT);
    public static final Image IMAGE_ABOUT_MENU_DEFAULT = new Image(IMAGE_URL_ABOUT_MENU_DEFAULT);

    private static final String IMAGE_PATH_PRINT_MENU_DARK = "/icons/menu/dark/print.png";
    private static final String IMAGE_PATH_CREATE_PDF_MENU_DARK = "/icons/menu/dark/pdf.png";
    private static final String IMAGE_PATH_UPDATE_MENU_DARK = "/icons/menu/dark/update.png";
    private static final String IMAGE_PATH_FAVORITE_MENU_DARK = "/icons/menu/dark/favorite.png";
    private static final String IMAGE_PATH_HISTORY_MENU_DARK = "/icons/menu/dark/history.png";
    private static final String IMAGE_PATH_DELETE_MENU_DARK = "/icons/menu/dark/delete.png";
    private static final String IMAGE_PATH_COPY_MENU_DARK = "/icons/menu/dark/copy.png";
    private static final String IMAGE_PATH_EXIT_MENU_DARK = "/icons/menu/dark/exit.png";
    private static final String IMAGE_PATH_ABOUT_MENU_DARK = "/icons/menu/dark/about.png";

    private static final String IMAGE_URL_PRINT_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_PRINT_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_CREATE_PDF_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_CREATE_PDF_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_UPDATE_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_UPDATE_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_FAVORITE_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_FAVORITE_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_HISTORY_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_HISTORY_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_DELETE_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_DELETE_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_COPY_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_COPY_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_EXIT_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_EXIT_MENU_DARK)).toExternalForm();
    private static final String IMAGE_URL_ABOUT_MENU_DARK = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_ABOUT_MENU_DARK)).toExternalForm();

    public static final Image IMAGE_PRINT_MENU_DARK = new Image(IMAGE_URL_PRINT_MENU_DARK);
    public static final Image IMAGE_CREATE_PDF_MENU_DARK = new Image(IMAGE_URL_CREATE_PDF_MENU_DARK);
    public static final Image IMAGE_UPDATE_MENU_DARK = new Image(IMAGE_URL_UPDATE_MENU_DARK);
    public static final Image IMAGE_FAVORITE_MENU_DARK = new Image(IMAGE_URL_FAVORITE_MENU_DARK);
    public static final Image IMAGE_HISTORY_MENU_DARK = new Image(IMAGE_URL_HISTORY_MENU_DARK);
    public static final Image IMAGE_DELETE_MENU_DARK = new Image(IMAGE_URL_DELETE_MENU_DARK);
    public static final Image IMAGE_COPY_MENU_DARK = new Image(IMAGE_URL_COPY_MENU_DARK);
    public static final Image IMAGE_EXIT_MENU_DARK = new Image(IMAGE_URL_EXIT_MENU_DARK);
    public static final Image IMAGE_ABOUT_MENU_DARK = new Image(IMAGE_URL_ABOUT_MENU_DARK);

    public static final Image[] IMAGE_PRINT_MENU = {IMAGE_PRINT_MENU_DEFAULT, IMAGE_PRINT_MENU_DARK};
    public static final Image[] IMAGE_CREATE_PDF_MENU = {IMAGE_CREATE_PDF_MENU_DEFAULT, IMAGE_CREATE_PDF_MENU_DARK};
    public static final Image[] IMAGE_UPDATE_MENU = {IMAGE_UPDATE_MENU_DEFAULT, IMAGE_UPDATE_MENU_DARK};
    public static final Image[] IMAGE_FAVORITE_MENU = {IMAGE_FAVORITE_MENU_DEFAULT, IMAGE_FAVORITE_MENU_DARK};
    public static final Image[] IMAGE_HISTORY_MENU = {IMAGE_HISTORY_MENU_DEFAULT, IMAGE_HISTORY_MENU_DARK};
    public static final Image[] IMAGE_DELETE_MENU = {IMAGE_DELETE_MENU_DEFAULT, IMAGE_DELETE_MENU_DARK};
    public static final Image[] IMAGE_COPY_MENU = {IMAGE_COPY_MENU_DEFAULT, IMAGE_COPY_MENU_DARK};
    public static final Image[] IMAGE_EXIT_MENU = {IMAGE_EXIT_MENU_DEFAULT, IMAGE_EXIT_MENU_DARK};
    public static final Image[] IMAGE_ABOUT_MENU = {IMAGE_ABOUT_MENU_DEFAULT, IMAGE_ABOUT_MENU_DARK};

    // ButtonClass
    /******************************************************************************************************************/

    public static final String BUTTON_ACTION_CLASS_TEXT = "button-action";

    // FieldPane
    /******************************************************************************************************************/

    public static final String EMPTY_TEXT = "";
    public static final String NOM_DU_MEDICAMENT_TEXT = "Nom du médicament";

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_NAME_WINDOWS_10_TEXT = "Windows 10";
    public static final String OS_DESKTOP_UI = System.getProperty("sun.desktop");
    public static final String OS_NAME_LINUX_TEXT = "Linux";
    public static final String OS_UI_GNOME_TEXT = "gnome";

    // FormPane
    /******************************************************************************************************************/

    public static final int COLUMN_INDEX_ZERO = 0;
    public static final int COLUMN_INDEX_ONE = 1;
    public static final int COLUMN_INDEX_TWO = 2;
    public static final int COLUMN_INDEX_THREE = 3;
    public static final int ROW_INDEX_ZERO = 0;
    public static final int ROW_INDEX_ONE = 1;
    public static final int ROW_INDEX_TWO = 2;
    public static final int ROW_INDEX_THREE = 3;
    public static final int ROW_INDEX_FOUR = 4;
    public static final int ROW_INDEX_FIVE = 5;
    public static final int ROW_INDEX_SIX = 6;
    public static final int COLUMNS_SPAN_ONE = 1;
    public static final int COLUMNS_SPAN_THREE = 3;
    public static final int COLUMNS_SPAN_FOUR = 4;
    public static final int ROWS_SPAN_ONE = 1;
    public static final int INSET_ZERO = 0;
    public static final int INSET_TWO = 2;
    public static final int INSET_EIGHT = 8;
    public static final int INSET_TEN = 10;
    public static final int INSET_FIFTEEN = 15;
    public static final int INSET_TWENTY_FIVE = 25;

    public static final int SKIP_INITIAL = 0;

    public static final String DATE_AMM_TEXT = "Date d'AMM";
    public static final String SPACE_DE_SPACE_TEXT = " de ";
    public static final String DATE_AMM_SPACE_DE_SPACE_TEXT = DATE_AMM_TEXT + SPACE_DE_SPACE_TEXT;
    public static final String DATE_AMM_AU_TEXT = " au ";
    public static final String COMMA_SPACE_TEXT = ", ";
    private static final String ESPECE_TEXT = "Espèce";
    private static final String ESPECES_TEXT = "Espèces";
    public static final String ESPECE_COLON_SPACE_TEXT = ESPECE_TEXT + COLON_SPACE_TEXT;
    public static final String ESPECES_COLON_SPACE_TEXT = ESPECES_TEXT + COLON_SPACE_TEXT;
    public static final String BUTTON_SEARCH_CLASS_TEXT = "button-search";
    public static final String NOM_DU_TITULAIRE_TEXT = "Nom du titulaire";
    public static final String NUMERO_AMM_TEXT = "Numéro d'AMM";
    public static final String LABEL_FORME_PHARMACEUTIQUE_TEXT = "Forme pharmaceutique";
    public static final String LABEL_TYPE_DE_PROCEDURE_TEXT = "Type de procédure";
    public static final String WINDOW_TITLE_DATE = "Date d'AMM";
    public static final String TEXTFLOW_CLASS_TEXT = "textflow";

    public static final double TEXT_LINE_SPACING = 4;
    public static final double WINDOW_DATE_STAGE_WIDTH = 250;
    public static final double WINDOW_DATE_STAGE_HEIGHT = 190;//194

    public static final String SUBSTANCE_ACTIVE_1_TEXT = "Substance active 1";
    public static final String SUBSTANCE_ACTIVE_2_TEXT = "Substance active 2";
    public static final String SUBSTANCE_ACTIVE_1_COLON_SPACE_TEXT = SUBSTANCE_ACTIVE_1_TEXT + COLON_SPACE_TEXT;
    public static final String SUBSTANCE_ACTIVE_2_COLON_SPACE_TEXT = SUBSTANCE_ACTIVE_2_TEXT + COLON_SPACE_TEXT;
    public static final String NEW_LINE_SUBSTANCE_ACTIVE_1_TEXT = PATTERN_NEWLINE + SUBSTANCE_ACTIVE_1_COLON_SPACE_TEXT;
    public static final String NEW_LINE_SUBSTANCE_ACTIVE_2_TEXT = PATTERN_NEWLINE + SUBSTANCE_ACTIVE_2_COLON_SPACE_TEXT;
    public static final double WINDOW_SUBSTANCE_ACTIVE_STAGE_WIDTH = 550;
    public static final double WINDOW_SUBSTANCE_ACTIVE_STAGE_HEIGHT = 158;//160

    public static final double WINDOW_ESPECE_STAGE_WIDTH = 710;
    public static final double WINDOW_ESPECE_STAGE_HEIGHT = 391;
    public static final int ESPECES_MAXIMUM_NUMBER = 22;

    public static final String[] EMPTY_ARRAY = {EMPTY_TEXT};

    public static final String FORM_PANE_CLASS_TEXT = "FormPane";
    private static final String BUTTON_DATES_TEXT = "Dates";
    private static final String BUTTON_ESPECES_TEXT = "Espèces";
    private static final String BUTTON_RECHERCHER_TEXT = "Rechercher";
    public static final Button BUTTON_DATE_AMM = new Button(BUTTON_DATES_TEXT);
    public static final Button BUTTON_SUBSTANCE_ACTIVE = new Button(SUBSTANCE_ACTIVE_PLURIEL_TEXT);
    public static final Button BUTTON_ESPECE = new Button(BUTTON_ESPECES_TEXT);
    public static final Button BUTTON_RECHERCHER = new Button(BUTTON_RECHERCHER_TEXT);

    public static final int DATE_BEGIN_YEAR = 1972;
    public static final int DATE_BEGIN_MONTH_ONE = 1;
    public static final int DATE_BEGIN_DAY_ONE = 1;

    // SearchResult, TableResult
    /******************************************************************************************************************/

    public static final int FONT_SIZE_NINE = 9;
    public static final int FONT_SIZE_TWELVE = 12;
    public static final int FONT_SIZE_FOURTEEN = 14;
    public static final int SKIP_HUNDRED = 100;
    public static final int DONNEES_MEDICAMENT_TAB_INDEX = 0;

    public static final String SKIP_TEXT = "-skip";
    public static final String WINDOW_TEXT = "window";
    public static final String FILENAME_RECHERCHE = "recherche";
    public static final String WEBVIEW_BOX_CLASS_TEXT = "webview-box";
    public static final String EMPTY_BODY_HTML = "<html><body></body></html>";
    public static final String WINDOW_GET_SELECTION_TOSTRING = "window.getSelection().toString()";
    public static final String PRINTER_TEXT = "-printer";

    public static final String DASH_TEXT = "-";
    public static final String SEARCH_RESULT_CLASS_TEXT = "SearchResult";

    public static final String FONT_PRINT = Objects.requireNonNull(Static.class.getResource("/ttf/Lato-Regular.ttf")).getFile();

    private static final String URL_STYLE_SHEET_WEBVIEW_DARK = Objects.requireNonNull(Static.class.getResource("/css/dark/webview.css")).toExternalForm();
    private static final String URL_STYLE_SHEET_WEBVIEW_DEFAULT = Objects.requireNonNull(Static.class.getResource("/css/default/webview.css")).toExternalForm();
    public static final String[] URL_STYLE_SHEET_WEBVIEW = {URL_STYLE_SHEET_WEBVIEW_DEFAULT, URL_STYLE_SHEET_WEBVIEW_DARK};

    // max (int) page.getMediaBox().getWidth();
    public static final int PAGE_WIDTH = 612;
    // max (int) page.getMediaBox().getHeight();
    public static final int PAGE_HEIGHT = 792;
    public static final int X_MARGIN = 35;
    public static final int Y_MARGIN = 32;
    public static final int COLOR_ANNOTATION = 130;

    private static final int COLOR_RED_GREEN_BLUE_LIGHT_BLACK = 85;
    private static final BackgroundFill BACKGROUND_FILL_LIGHT_BLACK = new BackgroundFill(javafx.scene.paint.Color.rgb(COLOR_RED_GREEN_BLUE_LIGHT_BLACK, COLOR_RED_GREEN_BLUE_LIGHT_BLACK, COLOR_RED_GREEN_BLUE_LIGHT_BLACK), CornerRadii.EMPTY, Insets.EMPTY);
    private static final int COLOR_RED_GREEN_BLUE_LIGHT_GREY = 238;
    private static final BackgroundFill BACKGROUND_FILL_LIGHT_GREY = new BackgroundFill(javafx.scene.paint.Color.rgb(COLOR_RED_GREEN_BLUE_LIGHT_GREY, COLOR_RED_GREEN_BLUE_LIGHT_GREY, COLOR_RED_GREEN_BLUE_LIGHT_GREY), CornerRadii.EMPTY, Insets.EMPTY);
    public static final Background[] WEBVIEW_BOX_LIGHT_BACKGROUNDS = {new Background(BACKGROUND_FILL_LIGHT_GREY), new Background(BACKGROUND_FILL_LIGHT_BLACK)};

    // TableResult
    /******************************************************************************************************************/

    public static final int INSET_TWENTY = 20;
    public static final int PAGE_WIDTH_DIVIDED_BY_2 = PAGE_WIDTH / 2;

    private static final String TOGGLE_BUTTON_RCP_TEXT = "RCP";
    private static final String IMAGE_PATH_RCP_BUTTON = "/icons/button/rcp.png";
    private static final String IMAGE_URL_RCP_BUTTON = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_RCP_BUTTON)).toExternalForm();
    public static final ImageView IMAGE_VIEW_RCP_BUTTON = new ImageView(new Image(IMAGE_URL_RCP_BUTTON));
    private static final String TOGGLE_BUTTON_FAV_TEXT = "FAV";
    private static final String IMAGE_PATH_FAVORITE_BUTTON = "/icons/button/favorite.png";
    private static final String IMAGE_URL_FAVORITE_BUTTON = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_FAVORITE_BUTTON)).toExternalForm();
    public static final ImageView IMAGE_VIEW_FAVORITE_BUTTON = new ImageView(new Image(IMAGE_URL_FAVORITE_BUTTON));
    private static final String BUTTON_UPDATE_TEXT = "MAJ";
    private static final String IMAGE_PATH_EDIT_BUTTON = "/icons/button/edit.png";
    private static final String IMAGE_URL_EDIT_BUTTON = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_EDIT_BUTTON)).toExternalForm();
    public static final ImageView IMAGE_VIEW_EDIT_BUTTON = new ImageView(new Image(IMAGE_URL_EDIT_BUTTON));
    public static final String TABLE_RESULT_CLASS_TEXT = "TableResult";
    public static final String MEDICAMENT_TEXT = "medicament";

    public static final String WINDOW_TITLE_UPDATE = "Mise à jour des informations";
    public static final int WINDOW_UPDATE_WIDTH = 710;
    public static final int WINDOW_UPDATE_HEIGHT = 330;

    public static final String AUCUNES_DONNEES = "<html><body class=\"body-result\">Le médicament n'est pas enregistré dans la base de données.</body></html>";

    public static final ButtonClass UPDATE_BUTTON = new ButtonClass(BUTTON_UPDATE_TEXT, IMAGE_VIEW_EDIT_BUTTON);
    public static final ToggleButtonClass RCP_TOGGLE_BUTTON = new ToggleButtonClass(TOGGLE_BUTTON_RCP_TEXT, IMAGE_VIEW_RCP_BUTTON);
    public static final ToggleButtonClass FAVORITE_TOGGLE_BUTTON = new ToggleButtonClass(TOGGLE_BUTTON_FAV_TEXT, IMAGE_VIEW_FAVORITE_BUTTON);

    // WindowAbout
    /******************************************************************************************************************/

    public static final String VBOX_BOTTOM_CLASS_TEXT = "vbox-bottom";
    public static final String CONTENT_CLASS_TEXT = "content";
    public static final String TITLE_TEXT = "Application médicament vétérinaire";
    public static final String HYPERLINK_TEXT = "Site web";
    public static final String VERSION_TEXT = "1.0";
    public static final String DATES_TEXT = "2020-2021";
    public static final String URL = "https://medicament-vet.ddns.net";
    public static final String WINDOW_ABOUT_CLASS_TEXT = "WindowAbout";

    // WindowClass
    /******************************************************************************************************************/

    public static final String OK_TEXT = "Ok";

    // WindowDelete
    /******************************************************************************************************************/

    public static final String BUTTON_SUPPRIMER_TEXT = "Supprimer";
    public static final String BUTTON_FERMER_TEXT = "Fermer";
    public static final String WINDOW_WARNING_TITLE_DELETE_TEXT = "Suppression du médicament";
    public static final String WINDOW_WARNING_MESSAGE_TEXT = "Le médicament n'est pas enregistré dans la base de données.";
    public static final String WINDOW_DELETE_CLASS_TEXT = "WindowDelete";

    public static final String URL_STYLE_SHEET_WINDOW_DARK = Objects.requireNonNull(Static.class.getResource("/css/dark/window.css")).toExternalForm();
    public static final String URL_STYLE_SHEET_WINDOW_DEFAULT = Objects.requireNonNull(Static.class.getResource("/css/default/window.css")).toExternalForm();
    public static final String[] URL_STYLE_SHEET_WINDOW = {URL_STYLE_SHEET_WINDOW_DEFAULT, URL_STYLE_SHEET_WINDOW_DARK};

    // WindowDoubleListView
    /******************************************************************************************************************/

    // France locale object comparator
    public static final Collator COLLATOR = Collator.getInstance(Locale.FRANCE);
    public static final String CHOIX_POSSIBLES_TEXT = "Choix possibles";
    public static final String SELECTION_TEXT = "Sélection";
    private static final String AJOUTER_TEXT = "Ajouter";
    private static final String ARROW_DOWN = "▼  ";
    private static final String ARROW_UP = "▲  ";
    public static final String BUTTON_ARROW_DOWN_AJOUTER_TEXT = ARROW_DOWN + AJOUTER_TEXT;
    public static final String BUTTON_ARROW_UP_SUPPRIMER_TEXT = ARROW_UP + BUTTON_SUPPRIMER_TEXT;
    public static final String POINT_TEXT = ".";
    public static final int CLICK_NUMBER_TWO = 2;

    // WindowDoubleListViewHorizontal
    /******************************************************************************************************************/

    public static final String WINDOW_DOUBLE_LIST_VIEW_HORIZONTAL_CLASS_TEXT = "WindowDoubleListViewHorizontal";

    // WindowDoubleListViewVertical
    /******************************************************************************************************************/

    public static final String BUTTON_GT_TEXT = ">>";
    public static final String BUTTON_LS_TEXT = "<<";
    public static final String WINDOW_DOUBLE_LIST_VIEW_VERTICAL_CLASS_TEXT = "WindowDoubleListViewVertical";

    // WindowFavorite
    /******************************************************************************************************************/

    public static final String WINDOW_FAVORITE_CLASS_TEXT = "WindowFavorite";

    public static final String BACKSLASH_TEXT = "\"";
    public static final String DOUBLE_BACKSLASH_TEXT = "\\\"";
    private static final String FAVORITE_DASH_TEXT = "favorite-";
    public static final String INSERT_JS_FAVORITE_FIRST_PART = "var div = document.getElementById(\"" + FAVORITE_DASH_TEXT;
    public static final String INSERT_JS_SECOND_PART = "\");div.insertAdjacentHTML(\"afterend\",\"";
    public static final String INSERT_JS_END_PART = "\");";

    public static final int LIMIT_ENTRY_NUMBER = 20;

    // WindowHistory
    /******************************************************************************************************************/

    public static final String BUTTON_SUPPRIMER_HISTORIQUE_TEXT = "Supprimer l'historique";
    public static final String BUTTON_HISTORY_CLASS_TEXT = "button-history";

    public static final String WINDOW_HISTORY_CLASS_TEXT = "WindowHistory";
    private static final String HISTORY_DASH_TEXT = "history-";
    public static final String INSERT_JS_HISTORY_FIRST_PART = "var div = document.getElementById(\"" + HISTORY_DASH_TEXT;

    // WindowWarning
    /******************************************************************************************************************/

    private static final String IMAGE_PATH_WARNING = "/icons/warning.png";
    private static final String IMAGE_URL_WARNING = Objects.requireNonNull(Static.class.getResource(IMAGE_PATH_WARNING)).toExternalForm();
    private static final ImageView IMAGE_VIEW_WARNING = new ImageView(new Image(IMAGE_URL_WARNING));
    public static final HBox H_BOX_IMAGE_VIEW_WARNING = new HBox(IMAGE_VIEW_WARNING);
    public static final String WINDOW_WARNING_CLASS_TEXT = "WindowWarning";
    public static final String HBOX_CONTENT_CLASS_TEXT = "hbox-content";
    public static final String HBOX_WARNING_IMAGE_CLASS_TEXT = "warning-image";
    public static final String HBOX_BOTTOM_CLASS_TEXT = "hbox-bottom";

    public static final Button BUTTON_OK = new Button(OK_TEXT);
    public static final double WINDOW_WARNING_WIDTH = 400;
    public static final double WINDOW_WARNING_HEIGHT = 140;
    public static final double MARGIN_WARNING_WIDTH = 100;
    public static final double HBOX_CONTENT_MINIMUM_HEIGHT = 48;

    // WindowDate
    /******************************************************************************************************************/

    public static final String DATE_DE_DEBUT_TEXT = "Date de début";
    public static final String DATE_DE_FIN_TEXT = "Date de fin";
    public static final Label LABEL_START_DATE = new Label(DATE_DE_DEBUT_TEXT);
    public static final Label LABEL_END_DATE = new Label(DATE_DE_FIN_TEXT);
    public static final String WINDOW_DATE_CLASS_TEXT = "WindowDate";

    // WindowEspece
    /******************************************************************************************************************/

    public static final String WINDOW_ESPECE_CLASS_TEXT = "WindowEspece";

    // WindowSubstanceActive
    /******************************************************************************************************************/

    public static final String WINDOW_SUBSTANCE_ACTIVE_CLASS_TEXT = "WindowSubstanceActive";

    // WindowComposition
    /******************************************************************************************************************/

    public static final String BUTTON_SUIVANT_TEXT = "Suivant";
    public static final String BUTTON_RETOUR_TEXT = "Retour";
    public static final String BUTTON_PLUS_TEXT = "+";
    public static final String BUTTON_MINUS_TEXT = "-";
    public static final String HBOX_ADD_SUBSTANCE_ACTIVE_CLASS_TEXT = "hbox-add-substance-active";
    public static final String LABEL_SUBSTANCE_ACTIVE_QUANTITE_UNITE_TEXT = "Substance active - Quantité - Unité";
    public static final String WINDOW_WARNING_TITLE_ERREUR = "Erreur";
    public static final String WINDOW_COMPOSITION_CLASS_TEXT = "WindowComposition";
    public static final Label LABEL_COMPOSITION = new Label(LABEL_SUBSTANCE_ACTIVE_QUANTITE_UNITE_TEXT);

    public static final String WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_TITRE = "Conditionnements primaires - Codes ATCVET - Mise à jour";
    public static final int WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_WIDTH = 710;
    public static final int WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_HEIGHT = 406;
    public static final int CONDITIONNEMENT_PRIMAIRE_CODE_ATC_MAXIMUM_NUMBER = 12;

    // WindowConditionDelivrance
    /******************************************************************************************************************/

    public static final String WINDOW_CONDITION_DELIVRANCE_CLASS_TEXT = "WindowConditionDelivrance";
    public static final String WINDOW_EXCIPIENT_QSP_TITRE = "Excipients QSP - Mise à jour";
    public static final double WINDOW_EXCIPIENT_QSP_WIDTH = 710;
    public static final double WINDOW_EXCIPIENT_QSP_HEIGHT = 391;
    public static final int EXCIPIENT_QSP_MAXIMUM_NUMBER = 5;

    // WindowConditionnementPrimaireCodeATC
    /******************************************************************************************************************/

    public static final String HBOX_ADD_CONDITIONNEMENT_PRIMAIRE_TEXT = "hbox-add-conditionnement-primaire";
    public static final String HBOX_ADD_CODE_ATCVET_CLASS_TEXT = "hbox-add-code-atcvet";
    public static final String FIELD_CLASS_TEXT = "field";
    public static final Label LABEL_CONDITIONNEMENT_PRIMAIRE = new Label(CONDITIONNEMENT_PRIMAIRE_SINGULIER_TEXT);
    public static final Label LABEL_CODE_ATCVET = new Label(CODE_ATCVET_SINGULIER_TEXT);

    public static final String WINDOW_CONDITIONNEMENT_PRIMAIRE_CODE_ATC_CLASS_TEXT = "WindowConditionnementPrimaireCodeATC";
    public static final String WINDOW_MODELE_DESTINE_VENTE_TITRE = "Modèles destinés à la vente - Mise à jour";
    public static final int WINDOW_MODELE_DESTINE_VENTE_WIDTH = 710;
    public static final int WINDOW_MODELE_DESTINE_VENTE_HEIGHT = 280;
    public static final int MODELE_DESTINE_VENTE_MAXIMUM_NUMBER = 100;

    // WindowEspeceUpdate
    /******************************************************************************************************************/

    public static final String WINDOW_ESPECE_UPDATE_CLASS_TEXT = "WindowEspeceUpdate";

    public static final String WINDOW_VOIE_ADMINISTRATION_TITRE = "Voies d'administration - Mise à jour";
    public static final double WINDOW_VOIE_ADMINISTRATION_WIDTH = 710;
    public static final double WINDOW_VOIE_ADMINISTRATION_HEIGHT = 391;
    public static final int VOIE_ADMINISTRATION_MAXIMUM_NUMBER = 10;

    // WindowExcipientQSP
    /******************************************************************************************************************/

    public static final String WINDOW_EXCIPIENT_QSP_CLASS_TEXT = "WindowExcipientQSP";

    public static final String WINDOW_COMPOSITION_TITRE = "Composition - Mise à jour";
    public static final double WINDOW_COMPOSITION_WIDTH = 710;
    public static final double WINDOW_COMPOSITION_HEIGHT = 280;
    public static final int COMPOSITION_MAXIMUM_NUMBER = 20;

    // WindowModeleDestineVente
    /******************************************************************************************************************/

    public static final String HBOX_ADD_MODELE_DESTINE_VENTE_CLASS_TEXT = "hbox-add-modele-destine-vente";
    public static final String BUTTON_METTRE_A_JOUR_TEXT = "Mettre à jour";
    public static final String FR_TEXT = "FR";
    public static final String LIBELLE_CODE_GTIN_TEXT = "Libellé - Code GTIN";
    public static final String LIBELLE_CODE_GTIN_NUMERO_AMM_TEXT = "Libellé - Code GTIN - Numéro d'AMM";
    public static final String WINDOW_MODELE_DESTINE_VENTE_CLASS_TEXT = "WindowModeleDestineVente";

    public static final String WINDOW_SAVE_UPDATE_TEXT = "WindowSaveUpdate";

    public static final String WINDOW_RCP_TITRE = "Résumé des caractéristiques du produit - Mise à jour";
    public static final int WINDOW_RCP_WIDTH = 710;
    public static final int WINDOW_RCP_HEIGHT = 430;

    // WindowRcp
    /******************************************************************************************************************/

    public static final String LIEN_RCP_TEXT = "Lien Rcp";
    public static final String VBOX_COMPONENT_LIST_CLASS_TEXT = "vbox-component-list";
    public static final String WINDOW_RCP_IMAGE_TITRE = "Résumé des caractéristiques du produit - Annexe Images - Mise à jour";
    public static final int WINDOW_RCP_IMAGE_WIDTH = 710;
    public static final int WINDOW_RCP_IMAGE_HEIGHT = 300;

    public static final String WINDOW_RCP_CLASS_TEXT = "WindowRcp";
    public static final String DATE_DE_VALIDATION = "Date de validation";
    public static final Label LABEL_DATE_VALIDATION = new Label(DATE_DE_VALIDATION);

    // WindowRcpImage
    /******************************************************************************************************************/

    public static final String CONNECTION_FAILED_RETRY = "Veuillez vérifier votre connexion internet et réessayer.";
    public static final String HBOX_ADD_IMAGE_CLASS_TEXT = "hbox-add-image";
    public static final String WINDOW_RCP_IMAGE_CLASS_TEXT = "WindowRcpImage";
    public static final Label LABEL_IMAGE_RCP_IMAGE = new Label(IMAGE_TEXT);

    // WindowUpdate
    /******************************************************************************************************************/

    public static final String LABEL_STATUT_AUTORISATION_TEXT = "Statut d'autorisation";
    public static final String HBOX_BOTTOMS_IMAGE_CLASS_TEXT = "hbox-buttons-image";
    public static final String HBOX_IMAGE_CLASS_TEXT = "hbox-image";
    public static final String ERROR_MESSAGE_PATH_TOO_LONG_TEXT = "Le chemin d'accès est trop long.";
    public static final int IMAGE_MAX_CHARACTERS = 400;
    public static final int ROW_INDEX_EIGHT = 8;
    public static final int COLUMNS_SPAN_TWO = 2;
    public static final int ROWS_SPAN_THREE = 3;
    public static final Label LABEL_FORME_PHARMACEUTIQUE = new Label(LABEL_FORME_PHARMACEUTIQUE_TEXT);
    public static final Label LABEL_STATUT_AUTORISATION = new Label(LABEL_STATUT_AUTORISATION_TEXT);
    public static final Label LABEL_TYPE_PROCEDURE = new Label(LABEL_TYPE_DE_PROCEDURE_TEXT);
    public static final Label LABEL_DATE_AMM = new Label(DATE_AMM_TEXT);
    public static final Label LABEL_IMAGE_UPDATE = new Label(IMAGE_TEXT);

    public static final String BUTTON_ANNULER_TEXT = "Annuler";
    public static final String WINDOW_UPDATE_CLASS_TEXT = "WindowUpdate";

    public static final String WINDOW_ESPECE_TITRE = "Espèces de destination - Mise à jour";
    public static final double WINDOW_ESPECE_UPDATE_STAGE_WIDTH = 710;
    public static final double WINDOW_ESPECE_UPDATE_STAGE_HEIGHT = 412;

    // WindowVoieAdministration
    /******************************************************************************************************************/

    public static final String WINDOW_VOIE_ADMINISTRATION_CLASS_TEXT = "WindowVoieAdministration";

    public static final String WINDOW_CONDITION_DELIVRANCE_TITRE = "Conditions de délivrance - Mise à jour";
    public static final double WINDOW_CONDITION_DELIVRANCE_WIDTH = 710;
    public static final double WINDOW_CONDITION_DELIVRANCE_HEIGHT = 412;
    public static final int CONDITION_DELIVRANCE_MAXIMUM_NUMBER = 5;

    // Services
    /******************************************************************************************************************/

    public static final String APPLICATION_JSON = "application/json";
    public static final String ACCEPT = "Accept";

    public static final String IMAGE_MEDICAMENT_TEXT = "imageMedicament";
    public static final String IMAGE_RCP_TEXT = "imageRcp";
    public static final String CONTENT_TYPE_TEXT = "Content-Type";
    public static final String STARTS_WITH_HTTP = "http";
    public static final String MULTIPART_FORM_DATA_BOUNDARY = "multipart/form-data;boundary=";

    public static final String API_URL = "https://medicament-vet.ddns.net/api";
    private static final String PATH_MEDICAMENTS_NOM_ID = "/medicaments/nom-id";
    public static final String PATH_MEDICAMENTS_SEARCH = "/medicaments/search";
    private static final String PATH_SEARCH_FORM = "/search-form";
    private static final String PATH_UPDATE_FORM = "/update-form";
    public static final String PATH_MEDICAMENT = "/medicament/";
    public static final String PATH_MEDICAMENT_UPDATE = "/medicament/update";

    public static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static final HttpRequest REQUEST_NOM_ID = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_MEDICAMENTS_NOM_ID)).build();
    public static final HttpRequest REQUEST_SEARCH_FORM = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_SEARCH_FORM)).build();
    public static final HttpRequest REQUEST_UPDATE_FORM = HttpRequest.newBuilder().header(ACCEPT, APPLICATION_JSON).uri(URI.create(API_URL + PATH_UPDATE_FORM)).build();

    // MultipartBodyPublisher
    /******************************************************************************************************************/

    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String DOUBLE_DASH = "--";
    public static final String CONTENT_DISPOSITION_TEXT = "Content-Disposition: form-data; name = ";
    public static final String CONTENT_TYPE_COLON_SPACE_TEXT = CONTENT_TYPE_TEXT + COLON_SPACE_TEXT;
    public static final String FILE_NAME_TEXT = "\"; filename = \"";

    // TaskProducer
    /******************************************************************************************************************/

    public static final String ERROR_INTERNET_CONNEXION = "Veuillez vérifier votre connexion internet";
    public static final String ERROR_INTERNET_CONNEXION_FILE_NOT_FOUND = "Veuillez vérifier votre connexion internet, ou un ou plusieurs de vos fichiers est/sont introuvable(s)";
    public static final String TITLE_DIALOG_RECHERCHE_PRINT = "Résultats de recherche";
    public static final String TITLE_DIALOG_INFORMATION_PRINT = "Informations du médicament";
    public static final String INTERRUPTION_IMPRESSION_TEXT = "Interruption de l'impression";
    public static final String SEARCH_RESULT_SKIP_CLASS_TEXT = SEARCH_RESULT_CLASS_TEXT + SKIP_TEXT;
    public static final String SEARCH_RESULT_PRINTER_CLASS_TEXT = SEARCH_RESULT_CLASS_TEXT + PRINTER_TEXT;
    public static final String TABLE_RESULT_PRINTER_CLASS_TEXT = TABLE_RESULT_CLASS_TEXT + PRINTER_TEXT;

    private static final int COLOR_RED_GREEN_BLUE_SLIGHTLY_LIGHT_BLACK = 68;
    private static final BackgroundFill BACKGROUND_FILL_SLIGHTLY_LIGHT_BLACK = new BackgroundFill(javafx.scene.paint.Color.rgb(COLOR_RED_GREEN_BLUE_SLIGHTLY_LIGHT_BLACK, COLOR_RED_GREEN_BLUE_SLIGHTLY_LIGHT_BLACK, COLOR_RED_GREEN_BLUE_SLIGHTLY_LIGHT_BLACK), CornerRadii.EMPTY, Insets.EMPTY);
    private static final BackgroundFill BACKGROUND_FILL_WHITE = new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY);
    public static final Background[] WEBVIEW_BOX_SLIGHTLY_LIGHT_BACKGROUNDS = {new Background(BACKGROUND_FILL_WHITE), new Background(BACKGROUND_FILL_SLIGHTLY_LIGHT_BLACK)};

    private static final String SEARCH_DASH_TEXT = "search-";
    public static final String INSERT_JS_SEARCH_FIRST_PART = "var div = document.getElementById(\"" + SEARCH_DASH_TEXT;
    public static final String INSERT_JS_APPEND_TRUE = "appendContent = true;";

    // Utils
    /******************************************************************************************************************/

    private static final String DATE_PATTERN_FR_1 = "dd/MM/yyyy";
    private static final String DATE_PATTERN_FR_2 = "dd-MM-yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_FR = new SimpleDateFormat(DATE_PATTERN_FR_1);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FR_1 = DateTimeFormatter.ofPattern(DATE_PATTERN_FR_1);
    public static final DateTimeFormatter DATE_TIME_FORMATTER_FR_2 = DateTimeFormatter.ofPattern(DATE_PATTERN_FR_2);

    public static final String USER_DIRECTORY = System.getProperty("user.dir");
    private static final String REGEX_IMAGE = "^[A-Za-z0-9. _-]+\\.[A-Za-z]{3,4}$";
    public static final Pattern PATTERN_IMAGE = Pattern.compile(REGEX_IMAGE);
    public static final double OPACITY_ZERO = 0;
    public static final double OPACITY_ONE = 1;
    public static final double DURATION_TIMELINE_TWO_HUNDRED = 200;
    public static final double DURATION_TIMELINE_FOUR_HUNDRED = 400;
    public static final String FOOTER_PAGE_TEXT = " - Page ";
    public static final String MESSAGE_ERREUR_PRINT = "L'impression n'est pas possible.";
    public static final String MESSAGE_ERREUR_PDF = "La génération du fichier PDF n'est pas possible.";
    public static final String BUTTON_CLASS_TEXT = "ButtonClass";
    public static final String DIALOG_TITLE_PDF_TEXT = "Créer un fichier PDF";
    public static final String DIALOG_TITLE_IMAGE_TEXT = "Sélectionner une image";
    public static final String STAR_TEXT = "*";
    public static final String PDF_EXTENSION_DESCRIPTION_TEXT = "Document PDF (*.pdf)";
    public static final String PDF_EXTENSION_TEXT = "pdf";
    public static final String JPG_EXTENSION_DESCRIPTION_TEXT = "JPG (*.jpg)";
    public static final String JPG_EXTENSION_TEXT = "jpg";
    public static final String JPEG_EXTENSION_DESCRIPTION_TEXT = "JPEG (*.jpeg)";
    public static final String JPEG_EXTENSION_TEXT = "jpeg";
    public static final String PNG_EXTENSION_DESCRIPTION_TEXT = "PNG (*.png)";
    public static final String PNG_EXTENSION_TEXT = "png";
    public static final String WINDOW_WARNING_TITLE_INCORRECT_FILE_TEXT = "Nom du fichier incorrect";
    public static final String WINDOW_WARNING_TITLE_INCORRECT_EXTENSION_TEXT = "Extension incorrecte";
    public static final String ERROR_MESSAGE_INCORRECT_FILE_TEXT = "Le nom du fichier est incorrect. Merci de saisir un nouveau nom.";
    public static final String ERROR_MESSAGE_INCORRECT_EXTENSION_TEXT = "L'extension de fichier est incorrect.";
    public static final String ERROR_MESSAGE_FILE_NOT_FOUND_1 = "Le fichier image ";
    public static final String ERROR_MESSAGE_FILE_NOT_FOUND_2 = " est introuvable.";
    public static final String WINDOW_WARNING_TITLE_ERROR_PRINT_SERVICE_TEXT = "Service d'impression";
    public static final String ERROR_MESSAGE_PRINT_SERVICE_NOT_FOUND = "Le service d'impression est introuvable.";

    public static final String BACKSLASH_DOUBLE = "\\";
    public static final String FORWARD_SLASH = "/";
    public static final Pattern SEPARATOR_3 = Pattern.compile(FORWARD_SLASH);
    public static final String FILE_PATH = "file:";
    public static final String FILE_PATH_FORWARD_SLASH = FILE_PATH + FORWARD_SLASH;

    private Static() {
        super();
    }
}
