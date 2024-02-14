package mx.uv.fiee.iinf.paradigmas.actividades;

public class ObjectTuple {
    private final Object first;
    private final Object second;

    public ObjectTuple (Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    public Object getFirst() {
        return first;
    }

    public Object getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first.toString(), second.toString());
    }
}