package fr.medicamentvet.services;

import com.fasterxml.jackson.core.type.TypeReference;
import fr.medicamentvet.entities.Medicament;
import fr.medicamentvet.entities.MedicamentSearch;
import fr.medicamentvet.entities.Rcp;
import fr.medicamentvet.entities.SearchForm;
import fr.medicamentvet.entities.UpdateForm;
import fr.medicamentvet.utils.MultipartBodyPublisher;
import fr.medicamentvet.utils.Static;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This class contains the HTTP request methods.
 */
public final class Services {

    /**
     * The method gets all nom and id (K,V) associated with Medicament object.
     *
     * @return {@code HashMap<String, Integer>}
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static Map<String, Integer> getAllMedicamentsNomId() throws IOException, InterruptedException {

        HttpResponse<String> response = Static.HTTP_CLIENT.send(Static.REQUEST_NOM_ID, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        return Static.OBJECT_MAPPER.readValue(response.body(), new TypeReference<Map<String, Integer>>() {
        });
    }

    /**
     * The method gets the inputs for SearchForm object.
     *
     * @return SearchForm object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static SearchForm getSearchFormInputs() throws IOException, InterruptedException {

        HttpResponse<String> response = Static.HTTP_CLIENT.send(Static.REQUEST_SEARCH_FORM, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        return Static.OBJECT_MAPPER.readValue(response.body(), SearchForm.class);
    }

    /**
     * The method gets the inputs for UpdateForm object.
     *
     * @return UpdateForm object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static UpdateForm getUpdateFormInputs() throws IOException, InterruptedException {

        HttpResponse<String> response = Static.HTTP_CLIENT.send(Static.REQUEST_UPDATE_FORM, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        return Static.OBJECT_MAPPER.readValue(response.body(), UpdateForm.class);
    }

    /**
     * The method returns a Medicament object by its id.
     *
     * @param id id of the Medicament object
     * @return Medicament object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static Medicament getMedicamentById(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().header(Static.ACCEPT, Static.APPLICATION_JSON).uri(URI.create(Static.API_URL + Static.PATH_MEDICAMENT + id)).build();

        HttpResponse<String> response = Static.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        return Static.OBJECT_MAPPER.readValue(response.body(), Medicament.class);
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

        StringWriter stringWriter = new StringWriter();

        Static.OBJECT_MAPPER.writeValue(stringWriter, medicament);

        // The order is important as the first (K,V) must be the Medicament object therefore a LinkedHashMap is created.
        Map<Object, Object> data = new LinkedHashMap<>();
        data.put(Static.MEDICAMENT_TEXT, stringWriter.toString());

        String imageURL = medicament.getImageURL();

        if (imageURL != null && !imageURL.startsWith(Static.STARTS_WITH_HTTP)) {
            data.put(Static.IMAGE_MEDICAMENT_TEXT, Path.of(imageURL));
        }

        Rcp rcp = medicament.getRcp();
        if (rcp != null) {
            List<String> imageURLList = rcp.getImageURLList();

            if (imageURLList != null) {
                for (int i = 0; i < imageURLList.size(); i++) {
                    String imageURLRcp = imageURLList.get(i);
                    if (!imageURLRcp.startsWith(Static.STARTS_WITH_HTTP)) {
                        data.put(Static.IMAGE_RCP_TEXT + Static.DASH_TEXT + (i + 1), Path.of(imageURLRcp));
                    }
                }
            }
        }

        String boundary = UUID.randomUUID().toString().replaceAll(Static.DASH_TEXT, Static.EMPTY_TEXT);

        // Create multi-part HTTP request with the help of MultipartBodyPublisher class
        HttpRequest request = HttpRequest.newBuilder().headers(Static.ACCEPT, Static.APPLICATION_JSON, Static.CONTENT_TYPE_TEXT, Static.MULTIPART_FORM_DATA_BOUNDARY + boundary).PUT(MultipartBodyPublisher.ofMimeMultipart(data, boundary)).uri(URI.create(Static.API_URL + Static.PATH_MEDICAMENT_UPDATE)).build();

        HttpResponse<String> response = Static.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        medicament = Static.OBJECT_MAPPER.readValue(response.body(), Medicament.class);

        return medicament;
    }

    /**
     * The method deletes all data associated with the Medicament object and the files, the directories.
     *
     * @param id id of the Medicament object
     * @throws IOException          Exception when there is I/O problem
     * @throws InterruptedException Exception when HTTP request is interrupted
     */
    public static void deleteMedicamentById(int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().header(Static.ACCEPT, Static.APPLICATION_JSON).DELETE().uri(URI.create(Static.API_URL + Static.PATH_MEDICAMENT + id)).build();

        HttpResponse<String> response = Static.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
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

        StringWriter stringWriter = new StringWriter();

        Static.OBJECT_MAPPER.writeValue(stringWriter, medicamentSearch);

        HttpRequest request = HttpRequest.newBuilder().headers(Static.ACCEPT, Static.APPLICATION_JSON, Static.CONTENT_TYPE_TEXT, Static.APPLICATION_JSON).POST(HttpRequest.BodyPublishers.ofString(stringWriter.toString(), StandardCharsets.UTF_8)).uri(URI.create(Static.API_URL + Static.PATH_MEDICAMENTS_SEARCH)).build();

        HttpResponse<String> response = Static.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        return Static.OBJECT_MAPPER.readValue(response.body(), new TypeReference<List<String>>() {
        });
    }
}
