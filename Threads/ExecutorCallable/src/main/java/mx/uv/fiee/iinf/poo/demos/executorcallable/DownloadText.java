package mx.uv.fiee.iinf.poo.demos.executorcallable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

public class DownloadText implements Callable<String> {
    private String _URL;

    @Override
    public String call() throws Exception {
        return doDownload ();
    }

    public DownloadText (String URL) {
        this._URL = URL;
    }

    private String doDownload () throws IOException {
        String result = null;
        java.net.URL loremURL = new URL (_URL);

        URLConnection urlConn = loremURL.openConnection ();
        HttpsURLConnection httpUrlConn = (HttpsURLConnection) urlConn;
        httpUrlConn.connect ();

        if (httpUrlConn.getResponseCode () == HttpURLConnection.HTTP_OK) {
            InputStream in = httpUrlConn.getInputStream ();
            InputStreamReader inputStreamReader = new InputStreamReader (in, StandardCharsets.UTF_8);
            BufferedReader buffReader = new BufferedReader (inputStreamReader);

            String line;
            StringBuilder builder = new StringBuilder ();
            while ((line = buffReader.readLine ()) != null) {
                builder.append (line);
                builder.append ("\n");
            }

            in.close ();
            result = builder.toString ();
        }

        httpUrlConn.disconnect ();
        return result;
    }
}



