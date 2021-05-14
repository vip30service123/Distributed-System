import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements TicTacToe {
    int[][] gameArray = new int[3][3];
    int player;
    int condition;

    public static void main(String[] args) {
        try {
            Server obj = new Server();

            TicTacToe stub = (TicTacToe) UnicastRemoteObject.exportObject(obj, 0);
            int[][] array = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    array[i][j] = 0;
                }
            }
            stub.setGameArray(array);
            stub.setCondition(0);
            stub.setPlayer(1);

            LocateRegistry.createRegistry(1);
            Registry registry = LocateRegistry.getRegistry(1);
            registry.bind("Tictactoe", stub);

            System.out.println("Server done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkWin(int[][] array) {
        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][1] == array[i][2] && array[i][0] != 0 && array[i][1] != 0 && array[i][2] != 0) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (array[0][i] == array[1][i] && array[1][i] == array[2][i] && array[0][i] != 0 && array[1][i] != 0 && array[2][i] != 0) {
                return true;
            }
        }

        if (array[0][0] == array[1][1] && array[1][1] == array[2][2] && array[0][0] != 0 && array[1][1] != 0 && array[2][2] != 0) {
            return true;
        }

        if (array[0][2] == array[1][1] && array[1][1] == array[2][0] && array[0][2] != 0 && array[1][1] != 0 && array[0][2] != 0) {
            return true;
        }

        return false;
    }

    public void display() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameArray[i][j] == 0) {
                    System.out.print("   ");
                } else if (this.gameArray[i][j] == 1) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" O ");
                }
                System.out.print("|");
            }
            System.out.println("");
            System.out.println("-----------");
        }
    }

    public boolean checkExist(int a, int b) {
        if (this.gameArray[a][b] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.gameArray[i][j] == 0) {
                    return false;
                }
            }
        }

        if (checkWin(this.gameArray) == false) {
            return true;
        }

        return false;
    }

    public void setArray(int a, int b, int player) {
        this.gameArray[a][b] = player;

        System.out.println("done");
    }

    public int[][] getGameArray() {
        return gameArray;
    }

    public void setGameArray(int[][] gameArray) {
        this.gameArray = gameArray;
    }

    @Override
    public int getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public int getCondition() {
        return condition;
    }

    @Override
    public void setCondition(int condition) {
        this.condition = condition;
    }
}
