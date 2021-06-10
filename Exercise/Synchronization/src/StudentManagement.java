import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.*;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

public class StudentManagement {
    //Add student to database
    public static void addToDataBase(String databaseName,Student student) {
        try {
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            Connection connection = DriverManager.getConnection(url,"root","123456");
            Statement statement = connection.createStatement();

            statement.executeUpdate("insert into student value (" + student.getId() +", \"" + student.getName() + "\", " + student.getGrade() + ")");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Convert database to student
    public static List<Student> dataBaseToStudent(String databaseName) {
        List<Student> students = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            Connection connection = DriverManager.getConnection(url,"root","123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getFloat(3));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }
    //Convert XML to student

    //Convert Document to XML string
    public static String convertDocToXMLString(Document document) {
        String output = "";

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            output = writer.getBuffer().toString();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return output;
    }
    //Convert student to Docuemnt
    public static Document convertStudentToDoc(Student student) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("student");
        doc.appendChild(root);

        Element idNode = doc.createElement("id");
        idNode.appendChild(doc.createTextNode(Integer.toString(student.getId())));
        root.appendChild(idNode);

        Element nameNode = doc.createElement("name");
        nameNode.appendChild(doc.createTextNode(student.getName()));
        root.appendChild(nameNode);

        Element gradeNode = doc.createElement("grade");
        gradeNode.appendChild(doc.createTextNode(Float.toString(student.getGrade())));
        root.appendChild(gradeNode);

        return doc;
    }
    //Convert XMLString to Document
    public static Document XMLStringToDocument(String XML) {
        Document doc = null;

        // convert xmlStr to Document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(XML)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    //Convert Document to Student
    public static Student docToStudent(Document document) {
        Student student = new Student();

        NodeList nodeList = document.getElementsByTagName("student");

        Node node = nodeList.item(0);

        Element element = (Element) node;

        student.setId(Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent()));
        student.setName(element.getElementsByTagName("name").item(0).getTextContent());
        student.setGrade(Float.parseFloat(element.getElementsByTagName("grade").item(0).getTextContent()));

        return student;
    }
    //Check Existed Student
    public static boolean check(Student student, List<Student> students) {
        for (Student student1 : students) {
            if (student.getId() == student1.getId()) return false;
        }
        return true;
    }
}
