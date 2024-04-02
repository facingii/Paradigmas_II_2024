package mx.uv.fiee.iinf.poo.demos.sortvectoractivity;

public class Utils {

    public static <T extends Number> void sort (T [] v) {
        if (v == null || v.length == 0) {
            return;
        }

        quicksort (v, 0, v.length - 1);
    }

    private static <T extends Number> void quicksort (T [] vector, int low, int high) {
        int i = low;
        int j = high;

        T pivote = vector [low + (high - low) / 2];

        while (i <= j) {
            while (vector [i].doubleValue () < pivote.doubleValue ()) {
                i++;
            }

            while (vector [j].doubleValue () > pivote.doubleValue ()) {
                j--;
            }

            if (i <= j) {
                T foo = vector [i];
                vector [i] = vector [j];
                vector [j] = foo;

                i++;
                j--;
            }
        }

        if (low < j) {
            quicksort (vector, low, j);
        }
        if (i < high) {
            quicksort (vector, i, high);
        }
    }
}
