package patterns.Strategy.strategies.impl;

import patterns.Strategy.strategies.QuackBehaviorStrategy;

public class DefaultQuackBehaviorStrategyImpl implements QuackBehaviorStrategy {
    @Override
    public void quack() {
        System.out.println("quack");
    }

    @Override
    public String toString() {
        return "DefaultQuackBehaviorStrategyImpl";
    }
}
