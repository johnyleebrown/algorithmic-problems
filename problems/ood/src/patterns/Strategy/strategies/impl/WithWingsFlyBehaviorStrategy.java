package patterns.Strategy.strategies.impl;

import patterns.Strategy.strategies.FlyBehaviorStrategy;

public class WithWingsFlyBehaviorStrategy implements FlyBehaviorStrategy {
    @Override
    public void fly() {
    }

    @Override
    public String toString() {
        return "WithWingsFlyBehaviorStrategy";
    }
}
