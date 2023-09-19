package skill.observer_pattern_demo;

public interface Observer<T> {
    void update(T topic);
}
