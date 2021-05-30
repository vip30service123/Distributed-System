package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main extends Application {

    @FXML TextField name;
    @FXML TextField id;
    @FXML TextField year;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public void submit(ActionEvent event) throws Exception{
        int serverPort = 1998;
        Socket socket = null;
        ObjectOutputStream toServer = null;
        ObjectInputStream fromServer = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            InetAddress serverHost = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);
            socket = new Socket(serverHost, serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());

            toServer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String statement = "insert into gui values ('" + name.getText() + "', '" + id.getText() + "', '" + year.getText() + "');";
            toServer.writeObject(statement);
            toServer.flush();

            fromServer = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            String msgFromReply = (String) fromServer.readObject();
            System.out.println(msgFromReply);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
