package mx.uv.fiee.iinf.poo;

/***
 * Java compara los tipos de dos formas:
 *
 * la función equals, realiza la comparación mediante el método hereado Object.equals a menos que sea sobreescrito por la clase hija
 * ==, aplicado a tipo primitivos compara el contenido de las variables y aplicado a objetos compara las referencias mismas.
 *
 */
public class Main {

    public static void main(String[] args) {
        System.out.println ("-----Tipos básicos-----");

        int a = 5;
        int b = 8;
        int c = 3;
        int d = 5;

        System.out.println (a == b); // false
        System.out.println (b == c); // false
        System.out.println (a == d); // true
        System.out.println (c != b); // true

        System.out.println ("-----Objetos-----");

        Persona foo = new Persona ("PERKAT");
        Persona bar = new Persona ("PERKAT");
        Persona aux = foo;

        System.out.println (foo == bar); // false
        System.out.println (foo == aux); // true
        System.out.println (foo.equals (bar)); // true

        System.out.println ("-----Objeto String-----");

        String objString1 = new String ("abcdef");
        String objString2 = new String ("abcdef");

        System.out.println (objString1 == objString2);
        System.out.println (objString1.equals (objString2));

        System.out.println ("-----Asignación directa de variables-----");

        String stringBasico1 = "lmnopqr";
        String stringBasico2 = "lmnopqr";

        System.out.println (stringBasico1 == stringBasico2);
        System.out.println (stringBasico1.equals (stringBasico2));
    }
}

/**
 *
 * https://www.ibm.com/developerworks/java/library/j-jtp05273/index.html
 *
 */
class Persona {
    private String name;

    public Persona (String name) {
        this.name = name;
    }

    public String getName () {
        return name;
    }

    /**
     * https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#equals-java.lang.Object-
     * @param obj to compare with
     * @return true or false
     */
    @Override
    public boolean equals (Object obj) {
        Persona p = (Persona) obj;
        return name.equals (p.getName ());
    }

    /**
     *
     * La documentación oficial recomienda que cada vez que se sobreescriba
     * la función equals, se reescriba igualmente la función HashCode,
     * de modo que el contrato de la función HashCode se mantiene. Dicho
     * contrato especifica que dos objetos iguales, deben tener el mismo
     * HashCode.
     *
     * Collections such as HashMap and HashSet use a hashcode value of an object to determine
     * how it should be stored inside a collection, and the hashcode is used again in order
     * to locate the object in its collection.
     *
     * @return unique value to identify this object
     */
    @Override
    public int hashCode () {
        // este valor es representativo, la función debe calcular
        // el valor hash real para el objeto
        return 1;
    }

}
