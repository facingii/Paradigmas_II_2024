package mx.uv.fiee.iinf.poo.agendatelefonica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static void main (String [] args) {
        // A grandes rasgos las expresiones regulares buscan patrones mediante comparaciones
        // individuales, esto es, comparan uno a uno si la entrada y el Pattern coinciden.
        //
        // Aquí creamos un patrón mediante el método compile, definiendo la estructura de la cadena
        // buscada, que debe cumplir algo así (###) ########
        //
        // las dobles diagonales se utilizan para escapar al caracter '\' que tiene significado
        // dentro del lenguaje
        //
        // la secuencia '\d' representa un número entre 0 y 9
        // la secuencia '\s' representa un espacio en blanco
        //
        // si analizan el patrón notarán que mediante la secuencia '\d{3}' definimos que después
        // de los paréntesis deberán existir 3 digitos
        //
        // posteriormente se indica mediante '\s{0,1}' que puede haber 0 o 1 espacio en blanco
        //
        // y por último, mediante '\d{7}' se define que después de los posibles espacios, deberá
        //haber como máximo 7 números
        Pattern pattern = Pattern.compile ("\\(\\d{3}\\)\\s{0,1}\\d{7}");

        // Una vez creado el patrón, se utiliza la clase matcher para iniciar la busqueda, pasando
        // como parámetro la entrada de busqueda
        Matcher matcher = pattern.matcher ("(123) 4567890");

        // si existen alguna correspodencia,
        if (matcher.find ()) {
            System.out.println (matcher.group ()); // imprimimos la cadena buscada
        }

    }


}
