import javax.sound.midi.Soundbank;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1);
            TicTacToe stub = (TicTacToe) registry.lookup("Tictactoe");

            while (stub.getCondition() == 0 && stub.checkDraw() == false) {
                while (stub.getPlayer() == 1 ) {
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

                System.out.println("Player X, enter your move (row[1-3] column[1-3]: ");

                Scanner scanner = new Scanner(System.in);
                int i = Integer.parseInt(scanner.nextLine());

                scanner = new Scanner(System.in);
                int j = Integer.parseInt(scanner.nextLine());

                while (i > 2 || i < 0 || j < 0 || j > 2) {
                    System.out.println("Wrong input");

                    scanner = new Scanner(System.in);
                    i = Integer.parseInt(scanner.nextLine());

                    scanner = new Scanner(System.in);
                    j = Integer.parseInt(scanner.nextLine());
                }

                while (stub.checkExist(i, j) == true) {
                    System.out.println("Wrong input");

                    scanner = new Scanner(System.in);
                    i = Integer.parseInt(scanner.nextLine());

                    scanner = new Scanner(System.in);
                    j = Integer.parseInt(scanner.nextLine());
                }

                stub.setArray(i, j, -1);

                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
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

                if (stub.checkWin(stub.getGameArray()) == true) {
                    stub.setCondition(-1);
                    System.out.println("Player 2 wins");
                    stub.setPlayer(1);
                    break;
                }

                stub.setPlayer(1);

                while (stub.getPlayer() == 1 ) {
                }
            }

            if (stub.checkDraw() == true) {
                System.out.println("Draw");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
