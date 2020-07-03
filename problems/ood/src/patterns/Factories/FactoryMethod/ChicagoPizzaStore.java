package patterns.Factories.FactoryMethod;

public class ChicagoPizzaStore extends PizzaStore {

    public ChicagoPizzaStore() {
        name = "ChicagoPizzaStore";
    }

    @Override
    protected Pizza createPizza(PizzaType type) {
        switch (type) {
            case PEPPERONI:
                return new ChicagoStylePepperoniPizza();
            case ARRABBIATA:
                return new ChicagoStyleArrabbiataPizza();
            default:
                return null;
        }
    }
}
