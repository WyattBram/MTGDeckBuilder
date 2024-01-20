import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

            Elements cardInfo = document.select(".productItemWrapper.productCardWrapper");
            System.out.println(cardInfo);


            for (Element ci: cardInfo){
                String title = ci.select(".stylePrice").text();

                System.out.println(title);
            }



        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
