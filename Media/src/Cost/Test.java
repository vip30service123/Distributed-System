package Cost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "123456");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select count(*) from book");

            System.out.println("Run");

            while (resultSet.next()) {
                System.out.println("Number of Book is " + Integer.parseInt(resultSet.getString(1)));
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
