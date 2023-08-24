package skill.design_datastructure.mq;

import java.io.IOException;

public class ProduceClientTest {
    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.produce("PRODUCER:hhhhhh");
    }
}
