public class Trick extends GroupOfCards{
    private int winner;
    private Card winningCard;
    private boolean hearts = false;
    private boolean queen = false;

    public Trick(int players){
        super(players * 2 - 1);
    }
    
    private boolean isWinner(Card card) {
		if (winningCard == null) {
			return true;
		}
		if (card.getSuit() == winningCard.getSuit() && card.getNum() > winningCard.getNum()) {
			return true;
		}
		return false;
	}
    
    public void update(int playerNum, Card card) {
    	addCard(card);
		if (isWinner(card)) {
			winner = playerNum;
			winningCard = card;
		}
		if (card.getSuit() == 2) {
			hearts = true;
		}
		if (card.getSuit() == 3 && card.getNum() == 12) {
			queen = true;
		}
	}

	public int getWinner() {
		return winner;
	}

	public Card getWinningCard() {
		return winningCard;
	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueen() {
		return queen;
	}
	
	public int getSuitLed(){
		if (getCurrentSize() > 0) {
			return getCard(0).getSuit();
		}
		return -1;
	}
}