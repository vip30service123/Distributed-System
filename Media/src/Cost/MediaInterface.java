package Cost;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MediaInterface extends Remote {
    void add(String name, String type, String kind) throws RemoteException;
    int countBook() throws RemoteException;
    int countNewspaper() throws RemoteException;
}
