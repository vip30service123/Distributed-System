import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    final static int ServerPort = 1999;

    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);

        InetAddress ip = InetAddress.getByName("localhost");

        Socket socket = new Socket(ip, ServerPort);

        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        Thread sendMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                String msg = "";

                while (!msg.equals("Bye!")) {

                    // read the message to deliver.
                    msg = scanner.nextLine();

                    try {
                        // write on the output stream
                        outputStream.writeUTF(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                String msg = "";

                while (true) {
                    try {
                        msg = inputStream.readUTF();
                        System.out.println(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        sendMessage.start();
        readMessage.start();
    }
}