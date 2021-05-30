import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
    static Vector<ClientHandler> clientHandlers = new Vector<>();

    static int i = 0;

    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(1999);

        Socket socket;

        while (true)
        {
            socket = serverSocket.accept();

            System.out.println("New client request received : " + socket);

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            ClientHandler mtch = new ClientHandler(socket,"client " + i, inputStream, outputStream);

            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            clientHandlers.add(mtch);

            t.start();

            i++;

        }
    }
}