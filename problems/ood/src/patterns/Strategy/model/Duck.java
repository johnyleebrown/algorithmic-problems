package patterns.Strategy.model;

import patterns.Strategy.strategies.FlyBehaviorStrategy;
import patterns.Strategy.strategies.QuackBehaviorStrategy;

/**
 * Using abstract here so developers wouldn't create instance of this class
 * also it gives us a lot of flexibility
 */
public abstract class Duck {

    String name;

    FlyBehaviorStrategy flyBehaviour;
    QuackBehaviorStrategy quackBehavior;

    public Duck(String name) {
        this.name = name;
    }

    public void performFly() {
        flyBehaviour.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void setFlyBehaviour(FlyBehaviorStrategy flyBehaviour) {
        this.flyBehaviour = flyBehaviour;
    }

    public void setQuackBehavior(QuackBehaviorStrategy quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", flyBehaviour=" + flyBehaviour +
                ", quackBehavior=" + quackBehavior +
                '}';
    }
}
