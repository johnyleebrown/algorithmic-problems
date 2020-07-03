package patterns.Factories.FactoryMethod;

public abstract class PizzaStore {
    String name;

    public Pizza orderPizza(PizzaType type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    protected abstract Pizza createPizza(PizzaType type);

    public String getName() {
        return name;
    }
}
