package es.ull.esit.utilities;

import java.util.Scanner;

public class UserInterface {

    private UserInterface() {}

    public static void defaultMenu() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println("Welcome");
            System.out.println("----------------------");
            System.out.println("(1) Automatic Mode");
            System.out.println("(2) Manual Mode");
            System.out.println("(3) End");
            System.out.println("----------------------");
            System.out.print("Please enter a number:");

            input = scanner.nextInt();

            switch (input) {
                case 1:
                    UserInterface.automaticMode();
                    break;
                case 2:
                    UserInterface.manualMode();
                    break;
                case 3:
                    System.out.println("Bye");
                    break;
                default:
                    break;
            }

        } while (input != 3);
    }

    private static void automaticMode() {

    }

    private static void manualMode() {

    }
}
