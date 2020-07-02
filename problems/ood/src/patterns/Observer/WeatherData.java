package patterns.Observer;

import java.util.LinkedList;
import java.util.List;

public class WeatherData implements Subject {

    List<Observer> observerList;

    int temp; // temperature
    float humidity;
    float pressure;

    public WeatherData() {
        this.observerList = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if (!observerList.remove(observer)) {
            throw new RuntimeException("Weather data does not contain this observer.");
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update(temp, humidity, pressure);
        }
    }

    public void setMeasurements(int temp, float humidity, float pressure) {
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;

        notifyObservers();
    }
}
