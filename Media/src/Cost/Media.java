package Cost;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Media extends Remote{

    void add(String name, String type) throws RemoteException;
}
