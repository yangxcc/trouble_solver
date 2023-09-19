package skill.observer_pattern_demo;

import java.util.ArrayList;
import java.util.List;

public class SubjectObject<T> implements Subject<T>{
    List<Observer<T>> observers;
    private int temperature;
    public SubjectObject() {
        this.observers = new ArrayList<>();
    }

    public SubjectObject(int _temperature) {
        this.observers = new ArrayList<>();
        this.temperature = _temperature;
    }
    @Override
    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(T topic) {
        for (Observer<T> observer : observers) {
            observer.update(topic);
        }
    }

    public void sendMessage(T topic) {
        if (temperature >= 100) {
            System.out.println("水开了，发送消息");
            notifyObserver(topic);
        }
    }
}
