package patterns.Strategy.model;

import patterns.Strategy.strategies.impl.WithWingsFlyBehaviorStrategy;

public class AmazingDuck extends Duck {
    public AmazingDuck(String name) {
        super(name);
        flyBehaviour = new WithWingsFlyBehaviorStrategy();
    }
}
