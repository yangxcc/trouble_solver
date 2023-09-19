package skill.observer_pattern_demo;

public class ObserverObject<T> implements Observer<T>{
    private String name;
    public ObserverObject(String name) {
        this.name = name;
    }

    @Override
    public void update(T topic) {
        System.out.println("我是" + name + ",我收到了：" + topic.toString() + "的消息");
    }
}
