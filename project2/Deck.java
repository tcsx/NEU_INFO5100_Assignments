public class Deck extends GroupOfCards{
    public static final int TOTAL_CARDS = 52;
    
    public Deck(){
        super(TOTAL_CARDS);
        for(int i = 0; i < 4; i++) {
            for (int j = 2; j <= 14; j++) {
				this.addCard(new Card(j, i));
			}
        }
        
    }

    public void shuffle(){
        for(int unshuffled = getCurrentSize(); unshuffled > 0; unshuffled--) {
            int index = (int)(Math.random() * unshuffled);
            addCard(removeCard(index));
        }
    }

    public Card dealCard(){
        return removeCard(0);
    }
    
    public Card removeCard(int num, int suit) {
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			if (card.getNum() == num && card.getSuit() == suit) {
				return removeCard(i);
			}
		}
		return null;
	}
}