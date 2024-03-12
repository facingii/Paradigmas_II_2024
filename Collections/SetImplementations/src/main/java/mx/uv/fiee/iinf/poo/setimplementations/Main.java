package mx.uv.fiee.iinf.poo.setimplementations;

import java.util.*;

public class Main {

    public static void main (String [] args) {

        /*var soccerTeams = new LinkedHashSet<String>();
        soccerTeams.add ("Liverpool");
        soccerTeams.add ("Manchester United");
        soccerTeams.add ("Barcelona");
        soccerTeams.add ("Real Madrid");
        soccerTeams.add ("Milan");
        soccerTeams.add ("Juventus");
        soccerTeams.add (null);

        for (String team: soccerTeams) {
            System.out.println (team);
        }

        System.out.println(soccerTeams.contains("Juventus"));*/

        TreeSet<String> employees = new TreeSet<> (Collections.reverseOrder());
        employees.add("uno");
        employees.add("dos");
        employees.add("cuatro");
        employees.add("diez");

        for (String e: employees) {
            System.out.println (e);
        }

    }

}


class Employee {
    String name;
    Double payment;
    public Employee (String name, Double payment) {
        this.name = name;
        this.payment = payment;
    }
    public String getName () { return name; }
    public Double getPayment () { return payment; }
}
