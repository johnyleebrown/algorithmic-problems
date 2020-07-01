package patterns.Strategy.strategies.impl;

import patterns.Strategy.strategies.FlyBehaviorStrategy;

public class NoFlyBehaviorStrategyImpl implements FlyBehaviorStrategy {
    @Override
    public void fly() {
    }

    @Override
    public String toString() {
        return "NoFlyBehaviorStrategyImpl";
    }
}
