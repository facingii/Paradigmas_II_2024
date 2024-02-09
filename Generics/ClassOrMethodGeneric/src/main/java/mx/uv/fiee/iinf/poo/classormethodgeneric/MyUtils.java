package mx.uv.fiee.iinf.poo.classormethodgeneric;

/**
 * Tipo parametrizado ó clase genérica
 * @author Gonzalo
 * @param <T> tipo de dato a utilizar dentro de la clase
 */
public class MyUtils<T> {
    T field;
    
    public void set (T item) {
        field = item;
    }
    
    public T get () {
        return field;
    }
    
    /**
     * Método parametrizado ó método genérico
     * @param <T> tipo de dato a utilizar exclusivamente dentro del método
     * @param seed semilla para cálculo del promedio
     * @return promedio de un conjunto de datos dado
     */
    public <T> float average (T seed) {
        return 0.1f;
    } 
}
