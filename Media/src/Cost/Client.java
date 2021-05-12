package Cost;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        System.out.println("Input Book or Newspaper");

        Scanner ob = new Scanner(System.in);
        String input = ob.nextLine();

        while (input.equals("Newspaper") == false  & input.equals("Book") == false) {
            System.out.println("Input Book or Newspaper");

            ob = new Scanner(System.in);
            input = ob.nextLine();
        }

        if (input.equals("Newspaper")) {
            try {
                Registry registry = LocateRegistry.getRegistry(1);

                Newspaper newspaper = new Newspaper();
                Media stub = (Media) registry.lookup("Newspaper");

                System.out.println("Newspaper name: ");
                ob = new Scanner(System.in);
                newspaper.setName(ob.nextLine());

                System.out.println("Newspaper type: ");
                ob = new Scanner(System.in);
                newspaper.setType(ob.nextLine());

                stub.add(newspaper.getName(), newspaper.getType());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Registry registry = LocateRegistry.getRegistry(1);

                Book book = new Book();
                Media stub = (Media) registry.lookup("Book");

                System.out.println("Book name: ");
                ob = new Scanner(System.in);
                book.setName(ob.nextLine());

                System.out.println("Book type: ");
                ob = new Scanner(System.in);
                book.setType(ob.nextLine());

                stub.add(book.getName(), book.getType());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
