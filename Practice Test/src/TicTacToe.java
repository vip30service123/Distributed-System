import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToe extends Remote {
    boolean checkWin(int[][] array) throws RemoteException;

    void display() throws RemoteException;

    void setArray(int a, int b, int player) throws RemoteException;

    boolean checkExist(int a, int b) throws RemoteException;

    boolean checkDraw() throws RemoteException;

    public int[][] getGameArray() throws RemoteException;

    public void setGameArray(int[][] gameArray) throws RemoteException;

    public int getPlayer() throws RemoteException;

    public void setPlayer(int player) throws RemoteException;

    public int getCondition() throws RemoteException;

    public void setCondition(int condition) throws RemoteException;
}
