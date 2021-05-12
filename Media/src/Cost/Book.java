package Cost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Book implements Media{
    String name;
    String type;

    public Book() {}

    Book(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void add(String name, String type) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "123456");
            Statement statement = connection.createStatement();
            String sql = "insert into media value (\"" + name +"\",\"\",\"" + type + "\")";
            System.out.println(sql);
            statement.executeUpdate(sql);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
