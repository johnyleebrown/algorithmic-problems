package patterns.Decorator;

public class EspressoBeverage extends AbstractBeverage {
    public EspressoBeverage() {
        description = "Espresso";
    }

    @Override
    public double calculateCost() {
        return 1.99;
    }
}
