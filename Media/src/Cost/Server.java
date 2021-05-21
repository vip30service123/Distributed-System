package Cost;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server implements MediaInterface {
    public static void main(String[] args) {
        try {
            Server obj = new Server();
            MediaInterface stub = (MediaInterface) UnicastRemoteObject.exportObject(obj, 0);

            LocateRegistry.createRegistry(1);
            Registry registry = LocateRegistry.getRegistry(1);
            registry.bind("Media", stub);

            System.out.println("Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(String name, String type, String kind) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "123456");
            Statement statement = connection.createStatement();
            if (kind.equals("Newspaper")) {
                statement.executeUpdate("insert into newspaper value (\"" + name + "\", \"" + type + "\")");
            }
            else {
                statement.executeUpdate("insert into book value (\"" + name + "\", \"" + type + "\")");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int countBook() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "123456");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from book");

            int count = 0;

            while (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString(1));
            }

            connection.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int countNewspaper() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "123456");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from newspaper");

            int count = 0;

            while (resultSet.next()) {
                count = Integer.parseInt(resultSet.getString(1));
            }

            connection.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
