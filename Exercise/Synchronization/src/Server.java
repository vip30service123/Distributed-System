import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) {
        Socket socket;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(1999);
            socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            List<Student> students = StudentManagement.dataBaseToStudent("db2");

            String message = "";
            while (!message.equals("end")) {
                message = dataInputStream.readUTF();
                if (!message.equals("end")) {
                    Student student = StudentManagement.docToStudent(StudentManagement.XMLStringToDocument(message));
                    if (StudentManagement.check(student, students)){
                        StudentManagement.addToDataBase("db2", student);
                    }
                }
            }

            for (Student student : students) {
                String XML = StudentManagement.convertDocToXMLString(StudentManagement.convertStudentToDoc(student));
                dataOutputStream.writeUTF(XML);
            }

            dataOutputStream.writeUTF("end");

            socket.close();
            dataInputStream.close();
            dataOutputStream.close();

        } catch (IOException | ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
