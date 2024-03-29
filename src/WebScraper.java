import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;


public class WebScraper {

    static int cn = 1;


    private static String formatPrices(String price) {
        price = price.replace(" ", "\n   ");
        price = price.replace("$", "Z");

        for (int i = 0; i < 4; i++) {

            if (i == 0) {
                price = price.replaceFirst("Z", "NM " + Matcher.quoteReplacement("$"));
            } else if (i == 1) {
                price = price.replaceFirst("Z", "EX " + Matcher.quoteReplacement("$"));
            } else if (i == 2) {
                price = price.replaceFirst("Z", "VG " + Matcher.quoteReplacement("$"));
            } else {
                price = price.replaceFirst("Z", "G " + Matcher.quoteReplacement("$"));
            }
        }
        return price;
    }


    protected void returnSingleInfo(String card, File file) {
        cn = 1;
        String editedCard = card.replace(" ", "+");

        String url = "https://www.cardkingdom.com/catalog/search?search=header&filter%5Bname%5D=" + editedCard;

        try {
            Document document = Jsoup.connect(url).get();

            Elements error = document.select(".no-results");

            if (!error.isEmpty()) {
                throw new NoCardException(card);
            }

            System.out.println();

            Elements cardInfo = document.select(".productItemWrapper.productCardWrapper");


            ArrayList<Card> cardList = new ArrayList<>();


            for (Element ci : cardInfo) {
                Elements set = ci.select(".productDetailSet");


                String price = ci.select(".stylePrice").text();
                String setType = set.select("a").text();
                String collecterNumber = set.select(".collector-number.d-none.d-sm-block").text();
                String cardName = ci.select("span.productDetailTitle").text();

                price = formatPrices(price);
                Card newCard = new Card(cardName, collecterNumber, setType, price, cn);
                cn++;
                cardList.add(newCard);
                newCard.printCard();


            }
            while (true) {
                int choice = 0;
                int temp = 0;
                Scanner scnr = new Scanner(System.in);

                System.out.println("Would you like to save one of these to your deck\n" +
                        "1. Yes\n" +
                        "2. No");
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

                    while (true) {

                        Scanner loopScanner = new Scanner(System.in);
                        System.out.println("\nWhich card number would you like to save");
                        System.out.print("- ");

                        try {
                            temp = loopScanner.nextInt() - 1;
                            loopScanner.nextLine();
                            if (temp > cardList.size() - 1 || temp < 0) {
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter a valid choice");
                        }
                    }

                    FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);


                    Card tempCard = cardList.get(temp);
                    fw.write("   " + tempCard.cardName);
                    fw.write("\n   " + tempCard.collecterNumber);
                    fw.write("\n   " + tempCard.setType + "\n");
                    fw.write("   " + tempCard.price);
                    fw.write("\n=======================================\n");

                    fw.flush();
                    break;

                } else if (choice == 2) {
                    return;

                }
            }


        } catch (NoCardException nce) {
            System.out.println(nce.getMessage());
        } catch (Exception e) {
            System.out.println("\nThere was an error, please try again!");
        }
    }


}
