package skill.observer_pattern_demo;

public class Main {
    public static void main(String[] args) {
        Observer<String> observer1 = new ObserverObject<>("1号观察者");
        Observer<String> observer2 = new ObserverObject<>("2号观察者");
        Observer<String> observer3 = new ObserverObject<>("3号观察者");

        SubjectObject<String> subject = new SubjectObject<>(190);
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer3);

        subject.sendMessage("水100度了");
    }
}
