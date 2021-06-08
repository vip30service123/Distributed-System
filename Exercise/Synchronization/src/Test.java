import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class Test {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "1", 1));
        students.add(new Student(2, "2", 2));
        students.add(new Student(3, "3", 3));

        Student student = new Student(1, "1", 1);
        System.out.println(students.indexOf(student));
    }
}
