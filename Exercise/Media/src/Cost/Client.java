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
                System.out.println(input);

                Registry registry = LocateRegistry.getRegistry(1);

                Newspaper newspaper = new Newspaper();
                MediaInterface stub = (MediaInterface) registry.lookup("Media");

                System.out.println("Newspaper name: ");
                ob = new Scanner(System.in);
                newspaper.setName(ob.nextLine());

                System.out.println("Newspaper type: ");
                ob = new Scanner(System.in);
                newspaper.setType(ob.nextLine());

                stub.add(newspaper.getName(), newspaper.getType(), input);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                System.out.println(input);

                Registry registry = LocateRegistry.getRegistry(1);

                Book book = new Book();
                MediaInterface stub = (MediaInterface) registry.lookup("Media");

                System.out.println("Book name: ");
                ob = new Scanner(System.in);
                book.setName(ob.nextLine());

                System.out.println("Book type: ");
                ob = new Scanner(System.in);
                book.setType(ob.nextLine());

                stub.add(book.getName(), book.getType(), input);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Do you want to know number of Book or Newspaper (Book, Newspaper): ");
        input = ob.nextLine();

        if (input.equals("Book")) {
            try {
                System.out.println(input);

                Registry registry = LocateRegistry.getRegistry(1);

                MediaInterface stub = (MediaInterface) registry.lookup("Media");

                System.out.println("Number of Book: " + stub.countBook());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (input.equals("Newspaper")) {
            try {
                System.out.println(input);

                Registry registry = LocateRegistry.getRegistry(1);

                MediaInterface stub = (MediaInterface) registry.lookup("Media");

                System.out.println("Number of Newspaper: " + stub.countNewspaper());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Wrong input");
        }
    }
}
