package patterns.Decorator;

public class CoffeeCafeService {
    public static void main(String[] args) {
        AbstractBeverage beverage = new EspressoBeverage();
        System.out.println("Can get " + beverage.getDescription() + " with default cost of " + beverage.calculateCost());
        beverage = new MilkBeverageAddon(beverage);
        System.out.println("But " + beverage.getDescription() + " will cost only " + beverage.calculateCost());
    }
}
