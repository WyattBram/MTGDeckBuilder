import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static void printActionMenu() {
        System.out.println("Menu: \n1. Card select \n2. Other Decks\n3. Quit");
        System.out.print("- ");
    }

    private static int getMenuChoice() {
        Scanner scnr = new Scanner(System.in);
        int menuChoice;

        while (true) {
            printActionMenu();

            menuChoice = scnr.nextInt();
            scnr.nextLine();
            if (menuChoice >= 1 && menuChoice < 4) {
                break;
            }
        }
        return menuChoice;
    }

    private static String getCardName() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Please enter the card name\n- ");

        return scnr.nextLine();

    }

    public static void main(String[] args) throws FileNotFoundException {

        //ws.returnSingleInfo("Jesters cap");

        System.out.println();
        System.out.println("""
                Welcome to MTGPrices! Explore the prices of Magic: The Gathering,\s
                cards both singles and mass entries. You can also conveniently
                export card costs to a file for easy access.
                """);


        do {
            WebScraper ws = new WebScraper();

            int cardChoice = getMenuChoice();

            if (cardChoice == 1) {

                ws.returnSingleInfo(getCardName());

            }

            if (cardChoice == 2) {



            }

            if (cardChoice == 3) {

                break;

            }


        } while (true);

    }
}