package mx.uv.fiee.iinf.poo;

public class MinMax<T extends Comparable<T>> implements IMinMax<T> {
    private final T [] values;

    public MinMax (T [] values) {
        this.values = values;
    }

    public T min () {
        T foo = values [0];

        for (var i: values) {
            if (foo.compareTo (i) > 0) {
                foo = i;
            }
        }

        return foo;
    }

    public T max  () {
        T foo = values [0];

        for (var i: values) {
            if (foo.compareTo (i) < 0) {
                foo = i;
            }
        }

        return foo;
    }

}