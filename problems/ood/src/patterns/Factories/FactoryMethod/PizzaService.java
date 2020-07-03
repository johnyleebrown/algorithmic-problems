package patterns.Factories.FactoryMethod;

public class PizzaService {
    public static void main(String[] args) {
        PizzaStore store = new NewYorkPizzaStore();
        Pizza pizza = store.orderPizza(PizzaType.PEPPERONI);
        System.out.println("Just Ordered " + pizza.getName() + " in " + store.getName());
    }
}
