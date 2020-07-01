package patterns.Strategy.model;

import patterns.Strategy.strategies.impl.DefaultQuackBehaviorStrategyImpl;
import patterns.Strategy.strategies.impl.NoFlyBehaviorStrategyImpl;

public class CityDuck extends Duck {
    public CityDuck(String name) {
        super(name);
        flyBehaviour = new NoFlyBehaviorStrategyImpl();
        quackBehavior = new DefaultQuackBehaviorStrategyImpl();
    }
}
