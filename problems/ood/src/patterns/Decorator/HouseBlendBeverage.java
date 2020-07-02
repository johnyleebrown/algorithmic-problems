package patterns.Decorator;

public class HouseBlendBeverage extends AbstractBeverage {
    public HouseBlendBeverage() {
        description = "HouseBlend";
    }

    @Override
    public double calculateCost() {
        return 2.99;
    }
}
