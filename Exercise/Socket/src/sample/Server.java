package sample;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server {
    public static void main(String[] args) {
        int serverPort = 1998;
        ServerSocket serverSocket = null;
        ObjectOutputStream toClient = null;
        ObjectInputStream fromClient = null;

        try {
            serverSocket = new ServerSocket(serverPort);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Just connected to " + socket.getRemoteSocketAddress());

                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                String number = (String) fromClient.readObject();

                try {
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=Student_Info";
                    Connection connection = DriverManager.getConnection(url, "sa", "123");

                    Statement statement = connection.createStatement();
                    statement.executeUpdate(number);

                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                toClient.writeObject(number);
                toClient.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}