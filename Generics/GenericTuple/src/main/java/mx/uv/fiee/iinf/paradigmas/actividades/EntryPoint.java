package mx.uv.fiee.iinf.paradigmas.actividades;

public class EntryPoint {

    public static void main(String[] args) {
        GenericTuple<Integer, Integer> point1 = new GenericTuple<>(1, 5);
        GenericTuple<Integer, Integer> point2 = new GenericTuple<>(1, 15);

        boolean result = Utilities.compare (point1, point2);
        System.out.println (result);

        boolean result2 = Utilities.compare ("Hola", "Hola");
        System.out.println (result2);

        boolean result3 = Utilities.compare (3.0, 3.0);
        System.out.println (result3);
    }

}
