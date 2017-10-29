public class GroupOfCards{
	private Card[] cards;
    private int currentSize = 0;

    public GroupOfCards(int number){
        cards = new Card[number];
    }
    
    public void addCard(Card card){
    	cards[currentSize] = card;
    	currentSize++;
    }
    
    public Card removeCard(int index) {
		Card c = cards[index];
    	for(int i = index; i < currentSize - 1; i++) {
			cards[i] = cards[i + 1];
		}
		currentSize--;
		return c;
    }

	public Card getCard(int i) {
		return cards[i];
	}

	public int getCurrentSize() {
		return currentSize;
	}

    public void display(){
		for (int i = 0; i < currentSize; i++) {
			cards[i].display();
		}
	}
}