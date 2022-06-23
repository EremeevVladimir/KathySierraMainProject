package Chapter15;

import java.io.PrintWriter;
import java.net.Socket;

public class TestClass {



    public static void main(String[] args) {

        try {
            Socket chatSocket = new Socket("127.0.0.1", 500);
            PrintWriter writer = new PrintWriter(chatSocket.getOutputStream());
            writer.println("Сообщение для отправки");
            writer.print("Другое сообщение");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
