public class Hand extends GroupOfCards {
	public final int NUM;
	private int shortest = 0;

	public Hand(int playerNum, int numberOfCards) {
		super(numberOfCards);
		NUM = playerNum;
	}

	public void sort() {
		for (int unsorted = getCurrentSize(); unsorted > 0; unsorted--) {
			int maxId = unsorted - 1;
			Card maxCard = getCard(maxId);
			int max = maxCard.getSuit() * 13 + maxCard.getNum();
			for (int i = 0; i < unsorted; i++) {
				Card currentCard = getCard(i);
				int current = currentCard.getSuit() * 13 + currentCard.getNum();
				if (current > max) {
					maxId = i;
					max = current;
					maxCard = currentCard;
				}
			}
			addCard(removeCard(maxId));
		}
	}

	public void setShortest() {
		int count0 = findCount(0);
		int count1 = findCount(1);
		int count3 = findCount(3);
		if (count0 > 0) {
			shortest = 0;
			if (count1 > 0 && count1 <= findCount(shortest)) {
				shortest = 1;
			}
			if (count3 > 0 && count3 <= findCount(shortest) && find(12, 3) == -1 && find(13, 3) == -1
					&& find(14, 3) == -1) {
				shortest = 3;
			}
		} else if (count1 > 0) {
			shortest = 1;
			if (count3 > 0 && count3 <= findCount(shortest) && find(12, 3) == -1 && find(13, 3) == -1
					&& find(14, 3) == -1) {
				shortest = 3;
			}
		} else if (count3 > 0) {
			if (find(12, 3) == -1 && find(13, 3) == -1 && find(14, 3) == -1) {
				shortest = 3;
			} else {
				shortest = 5;
			}
		} else {
			shortest = -1; // if there are only hearts left
		}
	}

	public int getShortest() {
		return shortest;
	}

	private int find(int num, int suit) {
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			if (card.getNum() == num && card.getSuit() == suit) {
				return i;
			}
		}
		return -1;
	}

	private int findCount(int suit) {
		int count = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			if (card.getSuit() == suit) {
				count++;
			}
		}
		return count;
	}

	public Card playACard(Game game, Trick trick) {
		int size = trick.getCurrentSize();
		int index;
		if (size == 0) {
			if (shortest == -1 || shortest == 5) {
				index = findLowest(game);
			} else {
				index = findHighest(shortest);
			}
		} else if (size == game.PLAYERS - 1 && !trick.getHearts() && !trick.getQueen()
				&& (index = findLastHigh(trick.getSuitLed())) >= 0)
			;
		else if ((index = findHighestBelow(trick.getWinningCard())) >= 0)
			;
		else if ((index = findMiddleHigh(game, trick.getSuitLed())) >= 0)
			;
		else if ((index = find(12, 3)) >= 0)
			; // queen of Spades
		else if ((index = find(14, 3)) >= 0)
			; // Ace of Spades
		else if ((index = find(13, 3)) >= 0)
			; // King of Spades
		else if ((index = findHighest(2)) >= 0)
			; // heart
		else {
			index = findHighest();
		}
		Card card = removeCard(index);
		setShortest(); //update shortest after remove the card
		trick.update(NUM, card);
		game.updateHeartsAndQueen(card);
		return card;
	}

	public int findLowest(int suit) {
		int lowest = 15;
		int lowestId = -1;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			if (card.getSuit() == suit && card.getNum() < lowest) {
					lowest = card.getNum();
					lowestId = i;
			}
		}
		return lowestId;
	}

	private int findLowest(Game game) {
		Card card = getCard(getCurrentSize() - 1);
		if (card.getSuit() == 2) {
			if (!game.getHearts()) {
				if (getShortest() == -1) {
					return -1;
				} else {
					return findLowest(3);
				}
			}
		}
		return getCurrentSize() - 1;
	}

	private int findHighest(int suit) {
		int highest = -1;
		int highestId = -1;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			int currentNum = card.getNum();
			if (card.getSuit() == suit && currentNum > highest) {
				highest = currentNum;
				highestId = i;
		}
		}
		return highestId;
	}

	private int findLastHigh(int suit) {
		int highest = -1;
		int highestId = -1;
		for (int i = 0; i < getCurrentSize(); i++) {
			Card card = getCard(i);
			int currentNum = card.getNum();
			if (suit == 3 && currentNum == 12) {
				continue;
			}
			if (card.getSuit() == suit && currentNum > highest) {
					highest = currentNum;
					highestId = i;
			}
		}
		return highestId;
	}

	private int findHighestBelow(Card winningCard) {
		int suit = winningCard.getSuit();
		int winningNum = winningCard.getNum();
		int i = findHighest(suit);
		if(i == - 1)
			return -1;
		Card card;
		while (i < getCurrentSize() && (card = getCard(i)).getSuit() == suit) {
			if (card.getNum() < winningNum) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private int findMiddleHigh(Game game, int suit) {
		if (suit == 3 && !game.getQueenOfSpades()) {
			int high = findHighestBelow(new Card(12, 3));
			if (high != -1)
				return high;
		}
		return findHighest(suit);
	}

	public int findHighest() {
		int highest = -1;
		int id = 0;
		for (int i = 0; i < getCurrentSize(); i++) {
			int num = getCard(i).getNum();
			if (num > highest) {
				highest = num;
				id = i;
			}
		}
		return id;
	}
}
