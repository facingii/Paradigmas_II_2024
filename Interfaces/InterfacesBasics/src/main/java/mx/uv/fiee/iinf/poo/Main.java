package mx.uv.fiee.iinf.poo;

public class Main {

    public static void main (String [] args) {
        Circle circle1 = new Circle (10, 10, 20);
        Circle circle2 = new Circle (15, 15, 21);
        circle1.calculateArea ();
        circle2.calculateArea ();

        int resultCircles = circle1.compareTo (circle2);
        System.out.println ("Circle1 is greater than Circle2? " + (resultCircles > 0));
        
        Rectangle rec1 = new Rectangle (10, 10, 20, 20);
        Rectangle rec2 = new Rectangle (30, 30, 50, 50);
        rec1.calculateArea ();
        rec2.calculateArea ();

        int resultRecs = rec1.compareTo (rec2);
        System.out.println ("Rectangle1 is greater than Circle2? " + (resultRecs > 0));
    }

}







