import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    int i;

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1);
            TicTacToe stub = (TicTacToe) registry.lookup("Tictactoe");

            int[][] array = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    array[i][j] = 0;
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (stub.getGameArray()[i][j] == 0) {
                        System.out.print("   ");
                    } else if (stub.getGameArray()[i][j] == 1){
                        System.out.print(" X ");
                    } else {
                        System.out.print(" O ");
                    }

                    System.out.print("|");
                }
                System.out.println("");
                System.out.println("-----------");
            }

            stub.display();
            System.out.println(stub.getPlayer() + "   " + stub.getCondition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
