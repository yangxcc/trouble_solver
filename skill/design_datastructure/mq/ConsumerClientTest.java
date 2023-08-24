package skill.design_datastructure.mq;

import java.io.IOException;

public class ConsumerClientTest {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        String msg = client.consumer();
        System.out.println(msg);

    }
}
