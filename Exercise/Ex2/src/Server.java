import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        ObjectOutputStream toClient = null;
        ObjectInputStream fromClient = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(1999);
            socket = serverSocket.accept();

            fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            List<Advertising> advertisingsServer = (List<Advertising>) fromClient.readObject();


            double totalTV = 0;
            double totalRadio = 0;
            double totalNewspaper = 0;
            double totalSales = 0;

            for (Advertising advertises : advertisingsServer) {
                totalTV += advertises.getTV();
                totalRadio += advertises.getRadio();
                totalNewspaper += advertises.getNewspaper();
                totalSales += advertises.getSales();
            }

            toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            toClient.writeObject("Done");
            toClient.flush();

            fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            String request = (String) fromClient.readObject();

            if (request.equals("TV")) {
                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                toClient.writeObject(Double.toString(totalTV));
                toClient.flush();
            }
            else if (request.equals("Radio")) {
                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                toClient.writeObject(Double.toString(totalRadio));
                toClient.flush();
            }
            else if (request.equals("Newspaper")) {
                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                toClient.writeObject(Double.toString(totalNewspaper));
                toClient.flush();
            }
            else if (request.equals("Sales")) {
                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                toClient.writeObject(Double.toString(totalSales));
                toClient.flush();
            }
            else {
                toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                toClient.writeObject("Wrong request");
                toClient.flush();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        socket.close();
        serverSocket.close();
    }
}
