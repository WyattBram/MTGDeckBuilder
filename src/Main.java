import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    private static void printActionMenu() {
        System.out.println("\nMenu: \n1. Card select \n2. Other Decks\n3. Quit");
        System.out.print("- ");
    }

    private static int getMenuChoice() {


        while (true) {
            Scanner scnr = new Scanner(System.in);
            int menuChoice = 0;
            printActionMenu();


            try {
                menuChoice = scnr.nextInt();
                scnr.nextLine();
                if (menuChoice < 1 || menuChoice > 3) {
                    throw new RuntimeException();
                }
                return menuChoice;
            } catch (Exception e) {
                System.out.println("Please make a valid choice");
            }

        }

    }

    private static File createNewDeckFile(int i) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("\nPlease enter the path that you want your " +
                "deck file saved to");
        System.out.print("- ");
        String fileName = scnr.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            for (int j = i; j < j + 1000; i++) {
                if (!new File(fileName + "\\Deck" + i + ".txt").exists()) {

                    Path path = Paths.get(fileName + "\\Deck" + i + ".txt");

                    try {
                        Path p = Files.createFile(path);
                        System.out.println("File Created at Path: " + p);

                    } catch (Exception e) {
                        System.out.println("Invalid path, try again");
                    }


                    return new File(fileName + "\\Deck" + i + ".txt");
                }
            }
        }
        if (!file.exists()) {
            System.out.println("Not valid path");
        }
        return createNewDeckFile(i + 1000);
    }

    private static String getCardName() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("\nPlease enter the card name\n- ");

        return scnr.nextLine();

    }

    public static File getDeck() {
        while (true) {
            Scanner scnr = new Scanner(System.in);
            int choice = 0;

            System.out.println("\nPlease select option\n" +
                    "1. Create new deck file\n" +
                    "2. Use existing deck file");
            System.out.print("- ");

            try {
                choice = scnr.nextInt();
                if (choice != 1 && choice != 2) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                System.out.println("Enter a valid choice");
            }


            if (choice == 1) {
                return createNewDeckFile(0);
            }
            if (choice == 2) {
                System.out.println("\nPlease enter the path of your deck");
                System.out.print("- ");
                scnr.nextLine();
                String fileName = scnr.nextLine();
                File file = new File(fileName);
                if (file.exists()) {
                    return file;
                } else {
                    System.out.println("Sorry that path is not valid");
                }
            }
        }


    }

    public static void main(String[] args) {

        System.out.println();
        System.out.print("""
                Welcome to MTGDeckBuilder! Explore the prices of Magic: The Gathering\s
                cards while you construct your perfect deck!
                """);

        File file = getDeck();


        do {
            WebScraper ws = new WebScraper();

            int cardChoice = getMenuChoice();

            if (cardChoice == 1) {

                ws.returnSingleInfo(getCardName(), file);

            }

            if (cardChoice == 2) {

                file = getDeck();

            }

            if (cardChoice == 3) {

                break;

            }


        } while (true);

    }
}