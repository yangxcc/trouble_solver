package skill.observer_pattern_demo;

public interface Subject<T> {
    void addObserver(Observer<T> observer);
    void removeObserver(Observer<T> observer);
    void notifyObserver(T topic);
}
