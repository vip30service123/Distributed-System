import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket = new Socket("localhost", 1999);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            List<Student> students = StudentManagement.dataBaseToStudent("db1");

            for (Student student : students) {
                String XML = StudentManagement.convertDocToXMLString(StudentManagement.convertStudentToDoc(student));
                dataOutputStream.writeUTF(XML);
            }

            dataOutputStream.writeUTF("end");

            String message = "";
            while (!message.equals("end")) {
                message = dataInputStream.readUTF();
                if (!message.equals("end")) {
                    Student student = StudentManagement.docToStudent(StudentManagement.XMLStringToDocument(message));
                    StudentManagement.addToDataBase("db1", student);
                }
            }

            socket.close();
            dataInputStream.close();
            dataOutputStream.close();

        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
