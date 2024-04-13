package mx.uv.fiee.iinf.poo.networks;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.HashMap;

public class EntryPoint {

    public static void main (String [] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder ()
                .followRedirects (HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest request = HttpRequest.newBuilder ()
                .uri (URI.create ("http://www.uv.mx"))
                .GET ()
                .build ();

        HttpResponse<Void> response = client.send (request, HttpResponse.BodyHandlers.discarding ());
        System.out.println ("Status code of requeted to url " + request.uri () + ": "  + response.statusCode ());

        System.out.print ("*******************************************************************************\n\n");

        // requesting only headers
        request = HttpRequest.newBuilder(URI.create ("http://www.uv.mx"))
                .method ("HEAD", HttpRequest.BodyPublishers.noBody ())
                .build ();

        response = client.send (request, HttpResponse.BodyHandlers.discarding());

        HttpHeaders headers = response.headers();

        headers.map().forEach((key, values) -> {
            System.out.printf("%s: %s%n", key, values);
        });

        System.out.print ("*******************************************************************************\n\n");

        // saving content into a file
        request = HttpRequest.newBuilder ()
                .uri (URI.create ("https://firebasestorage.googleapis.com/v0/b/departmentstorebackend.appspot.com/o/images%2Florem.txt?alt=media&token=83bfe244-d9fa-411d-879c-a7ca94dabed0"))
                .GET ()
                .build ();

        // this file is relative to the project
        var fileName = "loremipsum.txt";
        var response1 = client.send (request, HttpResponse.BodyHandlers.ofFile (Paths.get (fileName)));
        System.out.println ("file download with status code " + response1.statusCode ());

        System.out.print ("*******************************************************************************\n\n");

        // sending data to a URI
        var values = new HashMap<String, String>() {{
            put("name", "John Snow");
            put ("occupation", "Night Watcher");
        }};

        var objectMapper = new ObjectMapper ();
        String requestBody = objectMapper.writeValueAsString(values);
        System.out.println ("Body request: \n" + requestBody);

        request = HttpRequest.newBuilder ()
                .uri(URI.create ("https://httpbin.org/post"))
                .POST (HttpRequest.BodyPublishers.ofString(requestBody))
                .build ();

        var response2 = client.send (request, HttpResponse.BodyHandlers.ofString ());
        System.out.println ("\nBody response: \n" + response2.body ());
    }
}




