package Cost;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server{
    public static void main(String[] args) {
        try {
            Newspaper obj1 = new Newspaper();
            Book obj2 = new Book();

            Media stub1 = (Media) UnicastRemoteObject.exportObject(obj1, 0);
            Media stub2 = (Media) UnicastRemoteObject.exportObject(obj2, 0);


            LocateRegistry.createRegistry(1);
            Registry registry = LocateRegistry.getRegistry(1);
            registry.bind("Newspaper", stub1);
            registry.bind("Book", stub2);

            System.out.println("Ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
