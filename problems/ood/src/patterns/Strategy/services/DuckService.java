package patterns.Strategy.services;

import patterns.Strategy.model.CityDuck;
import patterns.Strategy.model.Duck;

public class DuckService {
    public void execute() {
        Duck d = new CityDuck("CityDuck");
        System.out.println(d.toString());
    }

    public static void main(String[] args) {
        new DuckService().execute();
    }
}
