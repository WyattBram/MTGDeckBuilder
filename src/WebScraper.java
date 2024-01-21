import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {

    public void returnSingleInfo(String card){

        String editedCard = card.replace(" ","+");

        String url = "https://www.cardkingdom.com/catalog/search?search=header&filter%5Bname%5D=" + editedCard;

        try{
            Document document = Jsoup.connect(url).get();

            Elements error = document.select(".no-results");



            if (!error.isEmpty()){
                System.out.println("Sorry, I can not find "+ card +". Please try again!");
                return;
            }

            System.out.println();

            Elements cardInfo = document.select(".productItemWrapper.productCardWrapper");

            //System.out.println(cardInfo);


            for (Element ci: cardInfo){
                Elements set = ci.select(".productDetailSet");


                String price = ci.select(".stylePrice").text();
                String setType = set.select("a").text();
                String collecterNumber = set.select(".collector-number.d-none.d-sm-block").text();
                String cardName = ci.select("span.productDetailTitle").text();

                price = price.replace(" ", "\n");
                price = price.replace("$","Z");

                for (int i = 0; i < 4; i++) {

                    if (i == 0){
                        price = price.replaceFirst("Z", "NM " + Matcher.quoteReplacement("$"));
                    }
                    else if (i == 1){
                        price = price.replaceFirst("Z", "EX " + Matcher.quoteReplacement("$"));
                    }
                    else if (i == 2){
                        price = price.replaceFirst("Z", "VG " + Matcher.quoteReplacement("$"));
                    }
                    else{
                        price = price.replaceFirst("Z", "G " + Matcher.quoteReplacement("$"));
                    }
                }

                System.out.println(cardName);
                System.out.println(collecterNumber);
                System.out.println(setType);
                System.out.println(price);
                System.out.println();


            }



        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
