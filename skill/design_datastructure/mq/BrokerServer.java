package skill.design_datastructure.mq;

import tree.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BrokerServer implements Runnable {
    public static final int PORT = 9999;
    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                String str = bufferedReader.readLine();
                if (str == null) {
                    continue;
                }
                System.out.println("接收到原始数据");
                if (str.equals("CONSUMER")) {
                    // 表示需要消费消息
                    String msg = Broker.consumer();
                    writer.println(msg);
                    writer.flush();
                } else if (str.contains("PRODUCER")) {
                    Broker.process(str);
                } else {
                    System.out.println("消息不符合规范，重新发送");
                    continue;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {
            BrokerServer brokerServer = new BrokerServer(serverSocket.accept());
            new Thread(brokerServer).start();
        }
    }
}
