package mx.uv.fiee.iinf.poo.basicgenerictype;

import java.util.ArrayList;

public class OtherEntry {

    public static void main(String[] args) {
//        ArrayList<Integer> intList = new ArrayList<> ();
//        intList.add (10);
//        intList.add (20);
//        Double d = Utilities.<Integer, Double>multiply (intList, 2.5);
//        System.out.println ("Resultado: " + d);


        var result = Utilities.DoSomething (30);
        System.out.println(result);
    }

}

class Utilities {
    public static <T, U> Double multiply(ArrayList<T> list, U val) {
        Double result = 0.0;

        for (int i = 0; i < list.size(); i++) {
            System.out.println (list.get(i));
            Double bar = ((Number) list.get(i)).doubleValue();
            System.out.println (bar);
            Double foo = ((Number) val).doubleValue();
            result += (bar * foo);
        }
        return result;
    }


    public static <T extends Double> Double DoSomething(T parameter) {
//        var type = parameter.getClass().getName();
//        System.out.println(type);
//
//        //if (type != "java.lang.Double")
//        if (!(parameter instanceof Double)) {
//            return 0.0;
//        }

//        var foo = (Double) parameter;
        return Math.pow(parameter, 10);
    }
}

class GenericSuperClass<T extends Number>
{
    //Generic super class with bounded type parameter
}

class GenericSubClass2 extends GenericSuperClass<Object>
{
    //type parameter replaced by sub class of upper bound
}

class GenericSubClass3<T extends Double> extends GenericSuperClass<T>
{
    //Compile time error
}












