package fr.medicamentvet.utils;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class creates a MIME multi-part data that can be sent to the server. The multi-part consists of a Medicament object, and possibly one or multiple images.
 */
public class MultipartBodyPublisher {

    public static HttpRequest.BodyPublisher ofMimeMultipart(Map<Object, Object> data, String boundary) throws IOException {

        byte[] separator = (Static.DOUBLE_DASH + boundary + Static.LINE_SEPARATOR + Static.CONTENT_DISPOSITION_TEXT).getBytes(StandardCharsets.UTF_8);

        List<byte[]> body = new ArrayList<>();

        for (Map.Entry<Object, Object> entry : data.entrySet()) {

            body.add(separator);

            if (entry.getValue() instanceof Path) {
                Path path = (Path) entry.getValue();
                String mimeType = fetchMimeType(path);

                body.add((Static.BACKSLASH_TEXT + entry.getKey() + Static.FILE_NAME_TEXT + path.getFileName() + Static.BACKSLASH_TEXT + Static.LINE_SEPARATOR + Static.CONTENT_TYPE_COLON_SPACE_TEXT + mimeType + Static.LINE_SEPARATOR + Static.LINE_SEPARATOR).getBytes(StandardCharsets.UTF_8));

                body.add(Files.readAllBytes(path));
                body.add(Static.LINE_SEPARATOR.getBytes(StandardCharsets.UTF_8));
            } else {
                body.add((Static.BACKSLASH_TEXT + entry.getKey() + Static.BACKSLASH_TEXT + Static.LINE_SEPARATOR + Static.CONTENT_TYPE_COLON_SPACE_TEXT + Static.APPLICATION_JSON + Static.LINE_SEPARATOR + Static.LINE_SEPARATOR + entry.getValue() + Static.LINE_SEPARATOR).getBytes(StandardCharsets.UTF_8));
            }
        }

        body.add((Static.DOUBLE_DASH + boundary + Static.DOUBLE_DASH).getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(body);
    }

    private static String fetchMimeType(Path fileNamePath) throws IOException {
        return "";
    }
}
