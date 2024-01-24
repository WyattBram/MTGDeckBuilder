import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;


public class WebScraper {


    private static String formatPrices(String price) {
        price = price.replace(" ", "\n");
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

    private static void printCard(String cardName, String collecterNumber, String setType, String price) {
        System.out.println(cardName);
        System.out.println(collecterNumber);
        System.out.println(setType);
        System.out.println(price);
        System.out.println();
    }

    protected void returnSingleInfo(String card, boolean massEntry) {

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


            for (Element ci : cardInfo) {
                Elements set = ci.select(".productDetailSet");


                String price = ci.select(".stylePrice").text();
                String setType = set.select("a").text();
                String collecterNumber = set.select(".collector-number.d-none.d-sm-block").text();
                String cardName = ci.select("span.productDetailTitle").text();

                price = formatPrices(price);
                printCard(cardName, collecterNumber, setType, price);

                if (massEntry){
                    Thread.sleep(1000);
                    break;
                }


            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
