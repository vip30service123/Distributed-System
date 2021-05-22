import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

class ClientHandler implements Runnable
{
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    Socket socket;
    boolean inUse;

    public ClientHandler(Socket socket, String name, DataInputStream inputStream, DataOutputStream outputStream) {

        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
        this.socket = socket;
        this.inUse=true;
    }

    @Override
    public void run() {

        try
        {
            String message = "";

            while (!message.equals("Bye!")){
                message = inputStream.readUTF();

                for (ClientHandler mc : Server.clientHandlers) {
                    if (!mc.name.equals(this.name) && mc.inUse == true) {
                        mc.outputStream.writeUTF(this.name + " say: " + message);
                    }
                }
            }

            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
