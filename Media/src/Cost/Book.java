package Cost;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Book extends Media{
    String name;
    String type;

    public Book() {}

    Book(String name, String type) {
        this.name = name;
        this.type = type;
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
