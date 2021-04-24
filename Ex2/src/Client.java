import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ObjectInputStream fromServer = null;
        ObjectOutputStream toServer = null;
        List<Advertising> advertisings = new ArrayList<Advertising>();

        try {
            socket = new Socket("localhost", 1999);

            System.out.println("Done");

            File file = new File("C:\\Users\\Acer\\Desktop\\advertising.csv");
            Scanner reader = new Scanner(file);

            String data = reader.nextLine();

            while (reader.hasNextLine()) {
                data = reader.nextLine();

                StringTokenizer token = new StringTokenizer(data, ",");

                double TV = Double.parseDouble(token.nextToken());
                double Radio = Double.parseDouble(token.nextToken());
                double Newspaper = Double.parseDouble(token.nextToken());
                double Sales = Double.parseDouble(token.nextToken());

                advertisings.add(new Advertising(TV, Radio, Newspaper, Sales));
            }

            toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            toServer.writeObject(advertisings);
            toServer.flush();

            fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            String status = (String) fromServer.readObject();
            System.out.println(status);

            System.out.println("Total TV, Radio, Newspaper or Sales: ");
            BufferedReader request = new BufferedReader(new InputStreamReader(System.in));

            toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            toServer.writeObject(request.readLine());
            toServer.flush();

            fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            String response = (String) fromServer.readObject();
            System.out.println(response);

            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
