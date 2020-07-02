package patterns.Observer;

public class CurrentStatusDisplay implements Observer, DisplayElement {

    int displayTemp;
    float displayHumidity;
    float displayPressure;

    WeatherData weatherData;

    @Override
    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public void update(int temp, float humidity, float pressure) {
        this.displayTemp = temp;
        this.displayHumidity = humidity;
        this.displayPressure = pressure;
    }

    @Override
    public String toString() {
        return "CurrentStatusDisplay{" +
                "displayTemp=" + displayTemp +
                ", displayHumidity=" + displayHumidity +
                ", displayPressure=" + displayPressure +
                '}';
    }
}
