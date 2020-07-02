package patterns.Decorator;

public abstract class AbstractBeverage {
    String description;

    public String getDescription() {
        return description;
    }

    public abstract double calculateCost();
}
