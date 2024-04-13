package mx.uv.fiie.iinf.paradigmas.weatherstations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Entry {
    private static final Hashtable<String, Double> temperatures = new Hashtable<>();
    private static BufferedReader readFile (String filepath) throws FileNotFoundException
    {
        File file = new File(filepath);
        FileReader reader = new FileReader(file);
        return new BufferedReader(reader);
    }

    private static void populateStorage (BufferedReader bufferedReader) throws IOException
    {
        String line;
        while ((line = bufferedReader.readLine ()) != null) {
            String [] parts = line.split (";");

            if (parts.length > 1) {
                temperatures.put (parts[0], Double.parseDouble (parts[1]));
            }
        }
    }

    private static void showAllCities ()
    {
        temperatures
                .forEach ((k, v) -> print (k, v));
    }

    private static void searchForACity (Scanner scan)
    {
        System.out.println ("What city are you looking for? ");
        String city = scan.next ();

        AtomicBoolean found = new AtomicBoolean (false);
        temperatures
                .forEach ((String k, Double v) -> {
                    if (k.toLowerCase (Locale.ENGLISH).contains (city.toLowerCase (Locale.ENGLISH)))
                    {
                        found.set (true);
                        print (k, v);
                    }
                });

        if (!found.get ()) {
            System.out.println ("Not found");
        }
    }

    private static void print (String city, Double temp) {
        String formattedString = String.format (Locale.ENGLISH, "%s temperature: %f", city, temp);
        System.out.println (formattedString);
    }

    public static void main (String [] args) {
        try {
            var bufferedReader = readFile ("/Users/gonzalo/Desktop/weather_stations.csv");
            populateStorage (bufferedReader);

            for (;;) {
                System.out.println("***********MENU***********");
                System.out.println("1. Show all cities");
                System.out.println("2. Search for a city");
                System.out.println("3. Exit");

                Scanner scan = new Scanner (System.in);
                System.out.println ("Enter an option:");
                int x = scan.nextInt ();

                switch (x) {
                    case 1:
                        showAllCities ();
                        break;
                    case 2:
                        searchForACity (scan);
                        break;
                    default:
                        System.exit (0);
                }
            }
        } catch (IOException fex) {
            fex.printStackTrace ();
        }
    }

}
