package patterns.Factories.FactoryMethod;

public class NewYorkPizzaStore extends PizzaStore {

    public NewYorkPizzaStore() {
        name = "NewYorkPizzaStore";
    }

    @Override
    protected Pizza createPizza(PizzaType type) {
        switch (type) {
            case PEPPERONI:
                return new NewYorkStylePepperoniPizza();
            case ARRABBIATA:
                return new NewYorkStyleArrabbiataPizza();
            default:
                return null;
        }
    }
}
