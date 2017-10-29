public class Card{
    private int num;
    private int suit;

    public Card(int num, int suit) {
        this.num = num;
        this.suit = suit;
    }

    public int getNum(){
        return num;
    }

    public int getSuit(){
        return suit;
    }

    public void display(){
        String strSuit;
        if (suit == 0) {
            strSuit = "clubs";
        }else if(suit == 1){
            strSuit = "diamonds";
        }else if (suit == 2) {
            strSuit = "hearts";
        }else {
            strSuit = "spades";
        }
        if (num < 11) {
            System.out.println(num + " of " + strSuit);
        }else if (num == 11) {
            System.out.println("Jack" + " of " + strSuit);
        }else if (num == 12) {
            System.out.println("Queen" + " of " + strSuit);
        }else if (num == 13) {
            System.out.println("King" + " of " + strSuit);
        }else if (num == 14) {
            System.out.println("Ace" + " of " + strSuit);
        }
    }
    
    public boolean isQueenOfSpades(){
    	return (num == 12 && suit == 3);
    }
}