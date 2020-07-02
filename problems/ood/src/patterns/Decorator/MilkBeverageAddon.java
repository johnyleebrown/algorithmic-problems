package patterns.Decorator;

public class MilkBeverageAddon extends AbstractBeverageAddon {
    AbstractBeverage abstractBeverage;

    public MilkBeverageAddon(AbstractBeverage abstractBeverage) {
        this.abstractBeverage = abstractBeverage;
    }

    @Override
    public String getDescription() {
        return abstractBeverage.getDescription() + " + Milk";
    }

    @Override
    public double calculateCost() {
        return abstractBeverage.calculateCost() + 0.49;
    }
}
