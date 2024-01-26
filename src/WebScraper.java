import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        Scanner scnr = new Scanner(System.in);
        String editedCard = card.replace(" ", "+");

        String url = "https://www.cardkingdom.com/catalog/search?search=header&filter%5Bname%5D=" + editedCard;

        try {
            Document document = Jsoup.connect(url).get();

            Elements error = document.select(".no-results");

            if (!error.isEmpty()) {
                System.out.println("Sorry, I can not find " + card + ". Please try again!");
                return;
            }

            System.out.println();

            Elements cardInfo = document.select(".productItemWrapper.productCardWrapper");

            //System.out.println(cardInfo);
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
            while (true){
                int choice = 0;
                int temp = 0;

                while(true) {
                    System.out.println("Would you like to add one of these cards to your deck\n" +
                            "1. Yes\n" +
                            "2. No\n-");
                    try {
                        choice = scnr.nextInt();

                        scnr.nextLine();

                        System.out.println(choice != 1);
                        System.out.println(choice != 2);
                        if (choice != 1 && choice != 2) {
                            throw new RuntimeException();
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Enter a valid choicerara");
                    }
                }



                if (choice == 1 ){
                    System.out.println("Which card number would you like to save\n- ");
                    while (true){
                        try{
                            temp = scnr.nextInt();
                            scnr.nextLine();
                            if (cardList.size()<=temp || temp<1){
                                throw new RuntimeException();
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter a valid choiceeeeee");
                        }
                    }

                    Scanner scanner = new Scanner(file);
                    PrintWriter pw = new PrintWriter(file);



                    Card tempCard = cardList.get(temp);
                    pw.println("   " + tempCard.cardName);
                    pw.println("   " + tempCard.collecterNumber);
                    pw.println("   " + tempCard.setType);
                    pw.println("   " + tempCard.price);
                    pw.println("=======================================");



                    pw.flush();
                    break;







                }




                else if (choice == 2 ){
                    return;

                }
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
