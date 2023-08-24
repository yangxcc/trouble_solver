package skill.design_datastructure.mq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void produce(String msg) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.PORT);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(msg);
        writer.flush();
        socket.close();
    }

    public String consumer() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), BrokerServer.PORT);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("CONSUMER");
            writer.flush();
            return bufferedReader.readLine();
        } finally {
            socket.close();
        }
    }
}
