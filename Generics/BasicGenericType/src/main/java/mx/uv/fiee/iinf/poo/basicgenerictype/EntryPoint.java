package mx.uv.fiee.iinf.poo.basicgenerictype;


import java.util.ArrayList;

public class EntryPoint {

    public static void main(String[] args) {
        AnyList<Student> list = new AnyList<> ();
        list.add (new Student ("Valery")); //sin boxing
        list.add (new Student ("Vicky"));
        list.add (new Student ("Veronica"));

        var list2 = new AnyList<Integer>();
        list2.add (5);

        Student foo = list.get (0); // Valery record… //sin conversiones
    }

}

class Student {
    private String name;
    public Student (String _name) {
        name = _name;
    }
}


class AnyList<T> {
    ArrayList<T> container = new ArrayList<> ();
    public void add (T item) {
        container.add (item);
    }
    public T get (int index) {
        return container.get(index);
    }
}
