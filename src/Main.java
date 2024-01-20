import java.util.Scanner;

public class Main {

    private static void printGameMenu() {
        System.out.println("Menu: \n1. Magic: The Gathering\n2. Pokemon\n3. Yu-Gi-Oh\n4. Quit\n");
    }

    private static void printActionMenu() {
        System.out.println("Menu: \n1. Single Prices\n2. Mass entry\n3. Back\n");
    }

    private static int getMenuChoice(int choicesGiven) {
        Scanner scnr = new Scanner(System.in);
        int menuChoice;

        while (true) {
            if (choicesGiven == 4) {
                printGameMenu();
            } else {
                printActionMenu();
            }
            menuChoice = scnr.nextInt();
            scnr.nextLine();
            if (menuChoice <= choicesGiven || menuChoice >= 1) {
                break;
            }
        }
        return menuChoice;
    }

    public static void main(String[] args) {

        WebScraper ws = new WebScraper();

        ws.returnSingleInfo("Jesters cap");

        //work on the grammer of this

        System.out.println("""
                Welcome to TCGPrices! Explore the prices of Magic: The Gathering,\s
                Pokemon, and Yu-Gi-Oh cards both singles and mass entries. You can
                also conveniently export card costs to a file for easy access.
                """);

        do {
            int cardChoice = getMenuChoice(4);

            if (cardChoice == 1) {
                do {

                    int typeChoice = getMenuChoice(3);

                    if (typeChoice == 3) {
                        break;
                    }

                } while (true);
            }

            if (cardChoice == 2) {
                do {

                    int typeChoice = getMenuChoice(3);

                    if (typeChoice == 3) {
                        break;
                    }

                } while (true);
            }

            if (cardChoice == 3) {
                do {

                    int typeChoice = getMenuChoice(3);

                    if (typeChoice == 3) {
                        break;
                    }

                } while (true);

            }

            if (cardChoice == 4) {
                break;
            }
        } while (true);

    }
}