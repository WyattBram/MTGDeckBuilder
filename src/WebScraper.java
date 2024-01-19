import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScraper {

    public static void returnSingleInfo(String card){

        card.replace(" ","+");

        String url = "https://www.tcgplayer.com/search/magic/product?productLineName=magic&productName="+ "" +"&view=grid";

        try{
            Document document = Jsoup.connect(url).get();
            //Work here
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
