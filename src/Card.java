public class Card {
    String cardName;
    String collecterNumber;
    String setType;
    String price;
    int cardNumber;

    Card(String cardName, String collecterNumber, String setType, String price, int cardNumber){
        this.cardName = cardName;
         this.collecterNumber =collecterNumber;
         this.setType = setType;
         this.price = price;
         this.cardNumber = cardNumber;
    }

    public void printCard(){
        System.out.println(cardNumber+ ". " + cardName);
        System.out.println("   " + collecterNumber);
        System.out.println("   " + setType);
        System.out.println("   " + price);
        System.out.println();
    }
}
