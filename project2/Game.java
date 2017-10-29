public class Game {
	public final int PLAYERS;
	private Deck deck;
	private Hand[] players;
	private Trick[] tricks;
	private int numberOfTricks = 0;
	private boolean hearts = false;
	private boolean queenOfSpades = false;

	public Game(int numberOfPlayers) {
		deck = new Deck();
		PLAYERS = numberOfPlayers;
		players = new Hand[numberOfPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Hand(i, 52 / numberOfPlayers);
		}
		tricks = new Trick[52 / numberOfPlayers];
	}

	public int getNumberOfTricks() {
		return numberOfTricks;

	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueenOfSpades() {
		return queenOfSpades;
	}

	public void playAGame() {
		deck.shuffle();
		int cardsLeft = 52 % PLAYERS;
		if (PLAYERS == 3) {
			deck.addCard(deck.removeCard(2, 1));
		}
		if (PLAYERS == 5) {
			deck.addCard(deck.removeCard(2, 1));
			deck.addCard(deck.removeCard(2, 0));
		}
		int maxCardNum = tricks.length;
		for (int i = 0; i < maxCardNum; i++) {
			for (Hand player : players) {
				player.addCard(deck.dealCard());
			}
		}
		int playerNum = 0;
		int lowestClub = 15;
		System.out.println("Output ¨C first part:\n");
		for (Hand player : players) {
			player.sort();
			player.setShortest();
			Card card = player.getCard(maxCardNum - 1);
			if (card.getSuit() == 0 && card.getNum() < lowestClub) {
				lowestClub = card.getNum();
				playerNum = player.NUM;
			}
			System.out.println("        player " + player.NUM + " shortest= " + player.getShortest());
			player.display();
		}
		Card card;
		int index;
		Hand player;
		System.out.println("");
		System.out.println("Output ¨C second part:\n");
		for (int i = 0; i < tricks.length; i++) {
			Trick trick = new Trick(PLAYERS);
			tricks[i] = trick;
			numberOfTricks++;
			if (i == 0) {
				player = players[playerNum];
				index = maxCardNum - 1;
				card = player.removeCard(index);
				trick.update(playerNum, card);
			} else {
				playerNum = tricks[i-1].getWinner();
				player = players[playerNum];
				card = player.playACard(this, trick);
			}
			System.out.print("player " + playerNum + "        ");
			card.display();
			for (int j = 0; j < PLAYERS - 1; j++) {
				playerNum = (playerNum + 1) % PLAYERS;
				player = players[playerNum];
				card = player.playACard(this, trick);
				System.out.print("player " + playerNum + "        ");
				card.display();
			}
			playerNum = trick.getWinner();
			if (i == 0) {
				for (int j = 0; j < deck.getCurrentSize(); j++) {
					System.out.print("undelt card     ");
					deck.getCard(j).display();
				}
			}
			System.out.println("");
		}
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println("Player " + i + " score= " + computePoints(i));
		}
	}

	public void updateHeartsAndQueen(Card card) {
		if (card.getSuit() == 2 && !hearts) {
			System.out.println("Hearts is now broken");
			hearts = true;
		}
		if (card.isQueenOfSpades()) {
			queenOfSpades = true;
		}
	}

	private int computePoints(int playerNum) {
		int points = 0;
		Card card;
		for (Trick trick : tricks) {
			if (trick.getWinner() == playerNum) {
				for (int i = 0; i < trick.getCurrentSize(); i++) {
					card = trick.getCard(i);
					if (card.getSuit() == 2) {
						points++;
					}
					if (card.isQueenOfSpades()) {
						points += 13;
					}
				}
			}
		}
		return points;
	}
}