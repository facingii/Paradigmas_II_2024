package mx.uv.fiee.iinf.poo.classormethodgeneric;

/**
 * clase cliente
 *
 * @author Gonzalo
 */
public class Main {

    public static void main (String [] args) {
        MyUtils<Integer> m = new MyUtils<> ();
        m.set (10);
        System.out.println (m.get ());

        float result = m.<Float>average (0.1f);
        System.out.println (result);
    }
}
